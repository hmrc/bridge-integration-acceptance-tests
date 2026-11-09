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

package uk.gov.hmrc.api.specs.CT

import models.ResponseBody
import org.scalatest.featurespec.FixtureAnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{GivenWhenThen, Outcome}
import steps.context.ResponseContext
import steps.helpers.CT.EnquiryHelper

class EnquiryFeatureSpec extends FixtureAnyFeatureSpec with GivenWhenThen with Matchers with EnquiryHelper {

  override type FixtureParam = ResponseContext

  override def withFixture(test: OneArgTest): Outcome = {
    val context = ResponseContext()
    try test(context)
    finally ()
  }

  Feature("Enquiry journey 1 test") {

    Scenario("Submit journey 1") { context =>
      val personForeignId                = "123456789567"
      When(s"the get request is sent to the dashboard api with $personForeignId")
      journey1(context, personForeignId, "24FE5D70-624C-4E86-9EAC-AD28DE7116D9", "CVW")
      Then("the response should contain the following details")
      val expectedResponse: ResponseBody =
        ResponseBody(message = "Job successfully submitted")
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }

  }

}
