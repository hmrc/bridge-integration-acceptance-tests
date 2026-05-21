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

package uk.gov.hmrc.api.specs

import models.RegisterRatepayerResponse
import org.scalatest.featurespec.FixtureAnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{GivenWhenThen, Outcome}
import steps.context.PropertyLinkingContext
import steps.helpers.PropertyLinkingStepHelper

class PropertyLinkingFeatureSpec
    extends FixtureAnyFeatureSpec
    with GivenWhenThen
    with Matchers
    with PropertyLinkingStepHelper {

  override type FixtureParam = PropertyLinkingContext

  override def withFixture(test: OneArgTest): Outcome = {
    val context = PropertyLinkingContext()
    try test(context)
    finally ()
  }

  Feature("PropertyLinking API Test") {

    Scenario("PropertyLinking Response") { context =>
      val personForeignId  = "123456789567"
      val assessmentId     = "27399677000"
      When(s"the get request is sent to the property linking api with $personForeignId and $assessmentId")
      propertyLinking(context, personForeignId, assessmentId)
      Then("the response should contain the following details")
      val expectedResponse = RegisterRatepayerResponse(message = "Job successfully submitted")
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }

  }

}
