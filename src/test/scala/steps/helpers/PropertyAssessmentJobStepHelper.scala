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

import builders.{PropertyAssessmentJobRequestBuilder, PropertyAssessmentRequestBuilder}
import models.{Persons, PropertyAssessmentContexted, RegisterRatepayerResponse}
import org.scalatest.matchers.should.Matchers
import play.api.libs.json.JsValue
import play.api.libs.ws.JsonBodyReadables.readableAsJson
import play.api.libs.ws.StandaloneWSResponse
import steps.context.{ExploreRatepayerContext, PropertyAssessmentContext, PropertyAssessmentJobContext}

trait PropertyAssessmentJobStepHelper { this: Matchers =>

  def propertyAssessment(
    context: PropertyAssessmentJobContext,
    credId: String
  ): Unit = {
    val response: StandaloneWSResponse = PropertyAssessmentJobRequestBuilder.propertyAssessmentData(credId)

    val jsonResponseBody = response.body[JsValue]
    context.status = response.status
    context.headers = response.headers.view.mapValues(_.mkString(", ")).toMap
  }

  def theResponseShouldContainTheFollowingDetails(
    context: PropertyAssessmentJobContext,
    expectedResponse: RegisterRatepayerResponse
  ): Unit =
    context.status shouldBe 200

}
