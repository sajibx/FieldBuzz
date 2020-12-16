package com.ss.fieldbuzz.Data

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

data class login(
    var app_top_module_assignment: List<Any>,
    var auth_info: AuthInfo,
    var organization_info: OrganizationInfo,
    var organization_logo: String,
    var organization_name: String,
    var success: Boolean,
    var token: String
)
{

    class Deserializer : ResponseDeserializable<login> {
        override fun deserialize(content: String): login = Gson().fromJson(content, login::class.java)!!
    }

    class ListDeserializer : ResponseDeserializable<List<login>> {
        override fun deserialize(reader: Reader): List<login> {
            val type = object : TypeToken<List<login>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}

data class AuthInfo(
    var assigned_to: Int,
    var designation: Any,
    var is_first_login: Boolean,
    var name: String,
    var role_id: Int,
    var role_name: String,
    var user_id: Int,
    var user_photo: Any,
    var user_type: String,
    var username: String
)

data class OrganizationInfo(
    var code: String,
    var id: Int,
    var name: String,
    var organization_status: Int,
    var project_name: String,
    var translated_project_name: String,
    var vat_registration_number: String
)




data class Posts(
    var applying_in: String,
    var cgpa: Double,
    var current_work_place_name: String,
    var cv_file: CvFile,
    var email: String,
    var expected_salary: Int,
    var experience_in_months: Int,
    var field_buzz_reference: String,
    var full_address: String,
    var github_project_url: String,
    var graduation_year: Int,
    var message: String,
    var name: String,
    var name_of_university: String,
    var on_spot_creation_time: Long,
    var on_spot_update_time: Long,
    var phone: String,
    var success: Boolean,
    var tsync_id: String
)

{

    class Deserializer : ResponseDeserializable<Posts> {
        override fun deserialize(content: String): Posts = Gson().fromJson(content, Posts::class.java)!!
    }

    class ListDeserializer : ResponseDeserializable<List<Posts>> {
        override fun deserialize(reader: Reader): List<Posts> {
            val type = object : TypeToken<List<Posts>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}

data class CvFile(
    var code: String,
    var date_created: Long,
    var description: Any,
    var extension: Any,
    var `file`: String,
    var id: Int,
    var last_updated: Long,
    var name: String,
    var path: String,
    var tsync_id: String
)

data class Resume(
    var code: String,
    var date_created: Long,
    var description: Any,
    var extension: Any,
    var file: String,
    var id: Int,
    var last_updated: Long,
    var message: String,
    var name: String,
    var path: String,
    var success: Boolean,
    var tsync_id: String
)
{

    class Deserializer : ResponseDeserializable<Resume> {
        override fun deserialize(content: String): Resume = Gson().fromJson(content, Resume::class.java)!!
    }

    class ListDeserializer : ResponseDeserializable<List<Resume>> {
        override fun deserialize(reader: Reader): List<Resume> {
            val type = object : TypeToken<List<Resume>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}
