package com.hosterdu.xenia.utils

import java.lang.reflect.Field
import java.lang.reflect.Modifier
import java.util.*
import javax.persistence.Entity
import kotlin.collections.HashMap
import kotlin.reflect.KCallable
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.declaredMembers
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType
import kotlin.reflect.jvm.javaGetter

class GraphqlTestUtils  {



    /**
     * method  for creating a graphql query
     * @param T class that the query returns
     * @param queryName name of the query being preformed
     * @param subQueries list of subclasses in t
     * @param params params for query where key is the param name and the value is a pair of param value and if the value is a string or not
     */
    fun getQuery(T: Class<*>, queryName: String, subQueries: List<Class<*>> = emptyList(), params: HashMap<String, Pair<String, Boolean>> = hashMapOf()): String{
       val fields =  T.declaredFields
        val builder = StringBuilder()
        builder.append("query{${queryName}")
        if(params.isNotEmpty())(addParams(params, builder))
        createReturnValues(fields, builder, subQueries)

        return builder.toString()
    }
    private fun createReturnValues(fields:Array<Field>, builder: StringBuilder,   subQueries: List<Class<*>>){
        builder.append("{ ")
        fields.forEach{
            if (subQueries.contains(it.type)){
                builder.append(getSubQuery(it.type, it.name, builder))
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

    fun getMutation(T: Class<*>, queryName: String, subQueries: List<Class<*>> = emptyList(), inputName: String, input: Any): String{
        val values = createValues(input)
        val inputType = T::class.java
        val fields =  T.declaredFields
        val builder = StringBuilder()
        builder.append("mutation{${queryName}")
        builder.append("($inputName: ")
        createInput(inputType, builder, values)
        builder.append(")")
        createReturnValues(fields,builder,subQueries)
        return builder.toString()

    }

    private fun createValues(obj: Any): List<Any>{
        val values = mutableListOf<Any>()
        obj.javaClass.kotlin.declaredMemberProperties.forEach {
            it.getter(obj)?.let { it1 -> values.add(it1) }
        }
        return values
    }

    private fun createInput(inputType: Class<*>, builder: StringBuilder, values: List<Any>){
        builder.append("{")
        val fields =  inputType.declaredFields
        println(fields.size)
        fields.forEachIndexed { index, field ->
            builder.append(field.name+ ":")
            if (field.type.isInstance(String)){
                builder.append("\"${values[index]}\"")
            }else{
                builder.append(values[index])
            }
            if(fields.size != 1 || index != fields.size-1) builder.append(", ")
        }
        builder.append("}")

    }
}