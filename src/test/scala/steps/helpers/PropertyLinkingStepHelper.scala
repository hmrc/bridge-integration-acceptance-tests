/*
 * Copyright 2026 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package steps.helpers

import builders.PropertyLinkingRequestBuilder
import models.RegisterRatepayerResponse
import org.scalatest.matchers.should.Matchers
import play.api.libs.json.JsValue
import play.api.libs.ws.JsonBodyReadables.readableAsJson
import play.api.libs.ws.StandaloneWSResponse
import steps.context.{GetPropertyLinksContext, PropertyLinkingContext}

trait PropertyLinkingStepHelper { this: Matchers =>

  def getPropertyLinks(context: GetPropertyLinksContext, credId: String): Unit = {

    val response: StandaloneWSResponse = PropertyLinkingRequestBuilder.getPropertyLinks(credId)
    println(s"STATUS: ${response.status}")
    println(s"BODY: ${response.body}")
    println(s"HEADERS: ${response.headers}")
    val jsonResponseBody               = response.body[JsValue]
    context.status = response.status
    context.headers = response.headers.view.mapValues(_.mkString(", ")).toMap
    context.responseBody = Some(jsonResponseBody)
  }

  def propertyLinking(
    context: PropertyLinkingContext,
    credId: String,
    assessmentId: String
  ): Unit = {
    val response: StandaloneWSResponse = PropertyLinkingRequestBuilder.propertyLinkingRequest(credId, assessmentId)

    val jsonResponseBody = response.body[JsValue]
    // context.responseBody = Some(jsonResponseBody.as[Properties])
    context.status = response.status
    context.headers = response.headers.view.mapValues(_.mkString(", ")).toMap
  }

  def theResponseShouldContainTheFollowingDetails(
    context: PropertyLinkingContext,
    expectedResponse: RegisterRatepayerResponse
  ): Unit =
    val actualResponseBody: Option[RegisterRatepayerResponse] = context.responseBody

    context.status shouldBe 200
    // actualResponseBody shouldBe Some(expectedResponse)

}
