package com.grupo2.plusorder.backend.tables

import com.grupo2.plusorder.backend.Backend.BASE_API
import com.grupo2.plusorder.backend.models.Categoria
import com.grupo2.plusorder.backend.models.Conta
import com.grupo2.plusorder.utils.DateUtils
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.util.*
import java.util.concurrent.CountDownLatch

object BackendConta {
    private const val BASE_EXTENSION = "Conta/"
    private const val LOGIN_EXTENSION = "postLogin/"

    fun GetAllContas() : List<Conta> {
        var contas = arrayListOf<Conta>()

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BASE_API + BASE_EXTENSION)
            .build()

        var countDownLatch = CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                countDownLatch.countDown()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful)
                        throw IOException("Unexpected code $response")

                    if (response.body != null) {
                        var result = response.body!!.string()
                        var resultArray = JSONArray(result)

                        for (index in 0 until resultArray.length()) {
                            var contaJSON = resultArray[index] as JSONObject
                            var conta = Conta.fromJSON(contaJSON)
                            contas.add(conta)
                        }
                    }

                    countDownLatch.countDown()
                }
            }
        })

        // await until request finished
        countDownLatch.await()

        return contas
    }

    fun GetConta(id: UUID) : Conta? {
        var conta: Conta? = null

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BASE_API + BASE_EXTENSION + id)
            .build()

        var countDownLatch = CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                countDownLatch.countDown()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful)
                        throw IOException("Unexpected code $response")

                    if (response.body != null) {
                        var result = response.body!!.string()
                        var resultJSONObject = JSONObject(result)
                        conta = Conta.fromJSON(resultJSONObject)
                    }

                    countDownLatch.countDown()
                }
            }
        })

        // await until request finished
        countDownLatch.await()

        return conta
    }

    // Adds object to database and returns uuid if successful
    fun AddConta(conta: Conta) : UUID? {
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body: RequestBody = RequestBody.create(
            mediaType, conta.toJSON().toString())

        var contaUUID: UUID? = null

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BASE_API + BASE_EXTENSION)
            .post(body)
            .build()

        var countDownLatch = CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                countDownLatch.countDown()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful)
                        throw IOException("Unexpected code $response")

                    if (response.body != null) {
                        var result = response.body!!.string()
                        var resultJSONObject = JSONObject(result)
                        contaUUID = Conta.fromJSON(resultJSONObject).id
                    }

                    countDownLatch.countDown()
                }
            }
        })

        // await until request finished
        countDownLatch.await()

        return contaUUID
    }

    fun UpdateConta(id: UUID, conta: Conta) : Boolean {
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body: RequestBody = RequestBody.create(
            mediaType, conta.toJSON().toString())

        var contaUpdated = false

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BASE_API + BASE_EXTENSION + id)
            .put(body)
            .build()

        var countDownLatch = CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                countDownLatch.countDown()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful)
                        throw IOException("Unexpected code $response")

                    if (response.body != null) {
                        var result = response.body!!.string()
                        var resultJSONObject = JSONObject(result)

                        val status = resultJSONObject.getString("status")
                        contaUpdated = status == "ok"
                    }

                    countDownLatch.countDown()
                }
            }
        })

        // await until request finished
        countDownLatch.await()

        return contaUpdated
    }

    fun DeleteConta(id: UUID) : Boolean {
        var contaDeleted = false

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BASE_API + BASE_EXTENSION + id)
            .delete()
            .build()

        var countDownLatch = CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                countDownLatch.countDown()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful)
                        throw IOException("Unexpected code $response")

                    if (response.body != null) {
                        var result = response.body!!.string()
                        var resultJSONObject = JSONObject(result)

                        val status = resultJSONObject.getString("status")
                        contaDeleted = status == "ok"
                    }

                    countDownLatch.countDown()
                }
            }
        })

        return contaDeleted
    }

    fun LoginConta(contaLogin: Conta) : Conta? {
        val mediaType = "application/json; charset=utf-8".toMediaType()
        val body: RequestBody = RequestBody.create(
            mediaType, contaLogin.toJSON().toString())

        var conta : Conta? = null

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BASE_API + BASE_EXTENSION + LOGIN_EXTENSION)
            .post(body)
            .build();

        var countDownLatch = CountDownLatch(1)
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                //AlertDialogUtils.ShowOkAlertBox("Não conseguimos conectar à Base de Dados", "Tente novamente.", AppContext.appContext)
                e.printStackTrace()
                countDownLatch.countDown()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful)
                        throw IOException("Unexpected code $response")

                    var result = response.body!!.string()
                    // Check if result has values
                    if (result != ""){
                        var resultJSONObject = JSONObject(result)
                        conta = Conta.fromJSON(resultJSONObject)
                    }

                    countDownLatch.countDown()
                }
            }
        })
        // await until post request finished
        countDownLatch.await()

        return conta
    }

    // no null-check
    fun GetAge(idConta: UUID) : Int {
        return DateUtils.GetAge(GetConta(idConta)!!.dataNasc!!)
    }
}