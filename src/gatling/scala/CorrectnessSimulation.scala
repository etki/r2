import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class CorrectnessSimulation extends Simulation {
  val httpConfiguration = http
    .baseURL("http://localhost:8080")
    .headers(Map(
      "Content-Type" -> "application/json",
      "Accept" -> "application/json"
    ))

  object Setup {
    val operations = scenario("Setup")
      .exec(
        http("Create source")
          .post("/v1/account")
          .body(StringBody("""{ "balance": "1000" }""")).asJSON
          .check(jsonPath("$.id").saveAs("source"))
      )
      .exec(
        http("Create target")
          .post("/v1/account")
          .body(StringBody("""{ "balance": "1000" }""")).asJSON
          .check(jsonPath("$.id").saveAs("target"))
      )
  }
  object Transfer {
    val operations = scenario("Transfer")
      .exec(
        http("Transfer")
          .post("/v1/transfer")
          .body(StringBody("""{ "source": "${source}", "target": "${target}", "amount": "0.1" }""")).asJSON
      )
  }

  setUp(
    Setup.operations.inject(atOnceUsers(1))
  ).protocols(httpConfiguration)
}
