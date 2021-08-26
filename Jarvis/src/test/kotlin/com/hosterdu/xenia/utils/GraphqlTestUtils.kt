package com.hosterdu.xenia.utils

import com.hosterdu.xenia.config.CustomSchemaGeneratorHooks
import java.lang.reflect.Field
import javax.persistence.Entity
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties

class GraphqlTestUtils {




        /**
         * method  for creating a graphql query
         * @param T class that the query returns
         * @param queryName name of the query being preformed
         * @param subQueries list of subclasses in t
         * @param params params for query where key is the param name and the value is a pair of param value and if the value is a string or not
         * @return return a graphql query for the given type
         */
        fun getQuery(T: Class<*>, queryName: String, subQueries: List<Class<*>> = emptyList(), params: HashMap<String, Pair<String, Boolean>> = hashMapOf()): String{
            val fields =  T.declaredFields
            val builder = StringBuilder()
            builder.append("query{${queryName}")
            if(params.isNotEmpty())(addParams(params, builder))
            createReturnValues(fields, builder, subQueries)

            return builder.toString()
        }
        private fun createReturnValues(fields:Array<Field>, builder: StringBuilder, subQueries: List<Class<*>>){
            builder.append("{ ")
            fields.forEach{
                if (subQueries.contains(it.type)){
                    getSubQuery(it.type, it.name, builder)
                }else if (!it.type.isAnnotationPresent(Entity::class.java)){
                    builder.append(it.name+" ")
                }

            }
            builder.append("}}")
        }

        private fun addParams(params: HashMap<String, Pair<String, Boolean>>, builder: StringBuilder){
            builder.append("(")
            var i = 0;
            params.forEach { (t, u) ->
                i++
                if(u.second){
                    builder.append("$t:\"${u.first}\" ")

                }else{
                    builder.append("$t:${u.first} ")
                }
                if(params.size != 1 || params.size != i ) builder.append(",")
            }
            builder.append(")")
        }


        private fun getSubQuery(T: Class<*>, fieldName: String, builder: StringBuilder){
            val fields =  T.declaredFields
            builder.append("${fieldName}{")
            fields.forEach {
                if (!it.type.isAnnotationPresent(Entity::class.java)){
                    builder.append(it.name+" ")
                }
            }
            builder.append("}")
        }

    /**
     * method  for creating a graphql mutation
     * @param T class that the query returns
     * @param queryName name of the query being preformed
     * @param subQueries list of subclasses in t
     * @param inputName is the name of the object being passed to the mutation
     * @param input is the actual object being passed to the mutation
     * @param inputType is the type of the object being passed to the mutation
     * @return return a graphql mutation for the given type with the given input
     */
    fun getMutation(T: Class<*>, queryName: String, subQueries: List<Class<*>> = emptyList(), inputName: String, input: Any, inputType: Class<*>): String{
            val values = createValues(input)
            val fields =  T.declaredFields
            val builder = StringBuilder()
            val input =
            builder.append("mutation{${queryName}")
            builder.append("($inputName: ")
            createInput(inputType, builder, values)
            builder.append(")")
            createReturnValues(fields,builder,subQueries)
            return builder.toString()

        }

        private fun createValues(obj: Any): HashMap<String, Any>{
            val values = hashMapOf<String, Any>()
            obj.javaClass.kotlin.declaredMemberProperties.forEach {
                values[it.name] = it.getter(obj)!!

            }
            return values
        }

        private fun createInput(inputType: Class<*>, builder: StringBuilder, values: HashMap<String, Any>){
            builder.append("{")
            val fields =  inputType.declaredFields
            fields.forEachIndexed { index, field ->
                if(values.size == index) return
                builder.append(field.name+ ":")

                if (field.type.isAssignableFrom(String::class.java) ||
                        CustomSchemaGeneratorHooks().isCustomType(field.type)){
                    builder.append("\"${values[field.name]}\"")
                }else{
                    builder.append(values[field.name])
                }
                if(fields.size != 1 && index != fields.size-1) builder.append(", ")

            }
            builder.append("}")

        }

}