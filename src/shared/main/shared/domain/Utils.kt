package shared.domain

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.base.CaseFormat
import java.io.Serializable
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object Utils {


    fun dateToString(dateTime: LocalDateTime): String? {
        return dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE)
    }

    fun dateToString(timestamp: Timestamp): String? {
        return dateToString(timestamp.toLocalDateTime())
    }

    fun jsonEncode(map: HashMap<String, Serializable>): String {
        return try {
            ObjectMapper().writeValueAsString(map)
        } catch (e: JsonProcessingException) {
            ""
        }
    }

    fun jsonDecode(body: String):
            HashMap<String, Serializable> {
        return ObjectMapper().readValue(body, HashMap::class.java) as HashMap<String, Serializable>

    }

    fun toSnake(text: String?): String? {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, text!!)
    }

    fun toCamel(text: String?): String? {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, text!!)
    }

    fun toCamelFirstLower(text: String?): String? {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, text!!)
    }

}