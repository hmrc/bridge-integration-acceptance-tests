package steps.helpers

import builders.PropertyAssessmentRequestBuilder
import models.PropertyAssessmentResponse
import org.scalatest.matchers.should.Matchers.shouldBe
import play.api.libs.json.{JsValue, Json}
import play.api.libs.json.OFormat.oFormatFromReadsAndOWrites
import play.api.libs.ws.JsonBodyReadables.readableAsJson
import play.api.libs.ws.StandaloneWSResponse
import steps.context.PropertyAssessmentContext

trait PropertyAssessmentStepHelper {

  def propertyAssessment(
    context: PropertyAssessmentContext,
    credId: String,
    assessmentId: String
  ): Unit = {
    val response: StandaloneWSResponse =
      PropertyAssessmentRequestBuilder.propertyAssessmentRequest(credId, assessmentId)

    val jsonResponseBody = response.body[JsValue]
    println(Json.prettyPrint(jsonResponseBody))
    context.responseBody = Some(jsonResponseBody.as[PropertyAssessmentResponse])
    context.status = response.status
    context.headers = response.headers.view.mapValues(_.mkString(", ")).toMap
  }

  def theResponseShouldContainTheFollowingDetails(
    context: PropertyAssessmentContext,
    expectedResponse: PropertyAssessmentResponse
  ): Unit = {
    val actualResponseBody: Option[PropertyAssessmentResponse] = context.responseBody

    context.status shouldBe 200
  }

}
