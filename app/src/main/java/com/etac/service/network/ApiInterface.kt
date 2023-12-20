package com.etac.service.network


import com.etac.service.models.ApplicationStatusResponse
import com.etac.service.models.auth.CheckOTPResponse
import com.etac.service.models.auth.LoginResponse
import com.etac.service.models.auth.SignUpResponse
import com.etac.service.models.service.ServiceCreateResponse
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET()
    fun getApplicationStatus(
        @Url url: String,
    ): Call<ApplicationStatusResponse>

    @POST()
    fun checkUser(
        @Url url:String,
        @Body jsonObject: JsonObject
    ): Call<LoginResponse>

    @POST()
    fun postSignUp(
        @Url url:String,
        @Body jsonObject: JsonObject
    ): Call<SignUpResponse>

    @POST()
    fun checkOTP(
        @Url url:String,
        @Body jsonObject: JsonObject
    ): Call<CheckOTPResponse>

    @POST()
    fun createServiceRea(
        @Url url:String,
        @Body jsonObject: JsonObject
    ): Call<ServiceCreateResponse>




























    /* @Headers(
     "Accept: application/json"
 )
 @POST(eAppApiEndPoint.CHECK_MOBILE_NUMBER)
 fun isMobileAvailable(
     @Body body: MobileIsAvailable
 ): Call<IsMobileAvailableResponse>*/

/*    @FormUrlEncoded
    @POST()
    fun submitCollectionEntryV2(
        @Url url: String,
        @Header("Authorization") authHeader: String?,
        @Field("deposit_type_id") deposit_type_id: Int,
        @Field("amount") amount: Int,
        @Field("bank_account_id") bank_account_id: Int,
        @Field("company_id") company_id: Int,
        @Field("deposit_date") deposit_date: String,
        @Field("depositor_bank_id") depositor_bank_id: Int,
        @Field("deposit_branch_id") deposit_branch_id: Int,
        @Field("deposit_branch_name") deposit_branch_name: String,
        @Field("image_link") image_link: String,
        @Field("remark") remark: String,
        @Field("lat") lat: Double,
        @Field("long") long: Double,
        @Header("X-RequestHash") requestHash: String,
        @Field("party_id") party_id: Int,
        @Field("receipt_id") receipt_id: String
    ): Call<SubmitCollectEntryResponse>*/




/*    @POST
    suspend fun submitOrderStatus(
        @Url url:String,
        @Header("Authorization") authHeader: String,
        @Header("X-RequestHash") requestHash: String,
        @Body jsonObj: JsonObject
    ): Response<OrderStatus>*/

}