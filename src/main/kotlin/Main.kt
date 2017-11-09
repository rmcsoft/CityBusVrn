package main.kotlin

import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.netty.Netty
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.routing.routing
import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import java.sql.DriverManager
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import org.jetbrains.ktor.request.receiveText
import org.jetbrains.ktor.routing.post
import java.sql.Connection


class Main(var connect: Connection = DriverManager
        .getConnection("jdbc:mysql://192.168.1.155/bus_vrn?" + "user=admin&password=admin&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC")
)

    fun main(args: Array<String>) {
        Class.forName("com.mysql.cj.jdbc.Driver")
        val main = Main()
        embeddedServer(Netty, 8080) {
            routing {
                get("/") {
                    //connectToMysql()
                    call.respondText(readFile("templates/index.html", Charset.forName("UTF-8")), ContentType.Text.Html)
                }
                post("/passenger/enter") {
                    val receivedData = call.receiveText()
                    val splittedData = receivedData.split(" ")
                    // [0] - bus_id
                    // [1] - stop_id
                    // [2] - NFC_id

                    call.respondText("trololo", ContentType.Text.Html)
                }
                post("/passenger/leave") {
                    val receivedData = call.receiveText()
                    val splittedData = receivedData.split(" ")
                    // [0] - bus_id
                    // [1] - stop_id
                    // [2] - NFC_id

                    call.respondText("trololo2", ContentType.Text.Html)
                }
            }
        }.start(wait = true)
    }

    @Throws(IOException::class)
    fun readFile(path: String, encoding: Charset) = String(Files.readAllBytes(Paths.get(path)), encoding)

fun createUserRecord(connect: Main, busId: String, stopId: String, nfcId: String, type: Int) {
    var preparedStatement = connect.connect
                .prepareStatement("insert into  user_records (bus_id, stop_id, user_id, time, type) values (?, ?, ?)")
    preparedStatement.setString(1, busId)
    //preparedStatement.setString(1, userId)

}
//
//fun connectToMysql() {
//    try {
//        // This will load the MySQL driver, each DB has its own driver
//
//        // Setup the connection with the DB
//        // Statements allow to issue SQL queries to the database
//        var statement = connect.createStatement()
//        // Result set get the result of the SQL query
//        var resultSet = statement
//                .executeQuery("select * from name")
//
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("first_name"))
//        }
//
//       // writeResultSet(resultSet)
////
////        // PreparedStatements can use variables and are more efficient
////        var preparedStatement = connect
////                .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)")
////        // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
////        // Parameters start with 1
////        preparedStatement.setString(1, "Test")
////        preparedStatement.setString(2, "TestEmail")
////        preparedStatement.setString(3, "TestWebpage")
////        preparedStatement.setDate(4, java.sql.Date(2009, 12, 11))
////        preparedStatement.setString(5, "TestSummary")
////        preparedStatement.setString(6, "TestComment")
////        preparedStatement.executeUpdate()
////
////        preparedStatement = connect
////                .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments")
////        resultSet = preparedStatement.executeQuery()
////        //writeResultSet(resultSet)
////
////        // Remove again the insert comment
////        preparedStatement = connect
////                .prepareStatement("delete from feedback.comments where myuser= ? ; ")
////        preparedStatement.setString(1, "Test")
////        preparedStatement.executeUpdate()
////
////        resultSet = statement
////                .executeQuery("select * from feedback.comments")
////        //writeMetaData(resultSet)
//
//    } catch (e: Exception) {
//        throw e
//    } finally {
//        connect.close()
//    }
//}
//

