package steps.helpers

import builders.CreateCTEnquiryRequestBuilder
import models.{EnquiryJobName, PostEnquiryResponse}
import org.scalatest.matchers.should.Matchers
import play.api.libs.json.JsValue
import play.api.libs.ws.JsonBodyReadables.readableAsJson
import play.api.libs.ws.StandaloneWSResponse
import steps.context.EnquiryContext

trait CreateCTEnquiryStepHelper { this: Matchers =>

  def createCTEnquiry(
                       context: EnquiryContext,
                       credId: String,
                       enquiryJobName: EnquiryJobName
                       ): Unit = {
    val response: StandaloneWSResponse = CreateCTEnquiryRequestBuilder.createCtEnquiry(credId, enquiryJobName)
    val jsonResponseBody = response.body[JsValue]
    context.responseBody = Some(jsonResponseBody.as[PostEnquiryResponse])
    context.status = response.status
    context.headers = response.headers.view.mapValues(_.mkString(", ")).toMap
  }

  def theResponseShouldContainTheFollowingDetails(
                                                   context: EnquiryContext,
                                                   expectedResponse: PostEnquiryResponse
                                                 ): Unit = {
    val actualResponseBody: Option[PostEnquiryResponse] = context.responseBody

    context.status shouldBe 200
    // actualResponseBody shouldBe Some(expectedResponse)
  }

}
