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

import models.{EnquiryJobName, PostEnquiryResponse}
import org.scalatest.{GivenWhenThen, Outcome}
import org.scalatest.featurespec.FixtureAnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import steps.context.EnquiryContext
import steps.helpers.CreateCTEnquiryStepHelper

class CreateCTEnquiryFeatureSpecs
    extends FixtureAnyFeatureSpec
    with GivenWhenThen
    with Matchers
    with CreateCTEnquiryStepHelper {

  override type FixtureParam = EnquiryContext

  override def withFixture(test: OneArgTest): Outcome = {
    val context = EnquiryContext()
    try test(context)
    finally ()
  }

  Feature("POST CT Enquiry  API Test") {

    Scenario("J09 ChangesToTheLocalArea AffectedMyProperty POST Enquiry") { context =>
      val personForeignId                       = "123456789567"
      When(s"the get request is sent to the dashboard api with $personForeignId")
      createCTEnquiry(context, personForeignId, EnquiryJobName.JOB_09)
      Then("the response should contain the following details")
      val expectedResponse: PostEnquiryResponse =
        PostEnquiryResponse(message = "Job successfully submitted")
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }

    Scenario("J08 CT to NDR Change for Business use POST Enquiry") { context =>
      val personForeignId                       = "123456789567"
      When(s"the get request is sent to the dashboard api with $personForeignId")
      createCTEnquiry(context, personForeignId, EnquiryJobName.JOB_08)
      Then("the response should contain the following details")
      val expectedResponse: PostEnquiryResponse =
        PostEnquiryResponse(message = "Job successfully submitted")
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }
    Scenario("J07 NDR to CT Change for Domestic purpose POST Enquiry") { context =>
      val personForeignId                       = "123456789567"
      When(s"the get request is sent to the dashboard api with $personForeignId")
      createCTEnquiry(context, personForeignId, EnquiryJobName.JOB_07)
      Then("the response should contain the following details")
      val expectedResponse: PostEnquiryResponse =
        PostEnquiryResponse(message = "Job successfully submitted")
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }
    Scenario("J06 Split the property into one or more property POST Enquiry") { context =>
      val personForeignId                       = "123456789567"
      When(s"the get request is sent to the dashboard api with $personForeignId")
      createCTEnquiry(context, personForeignId, EnquiryJobName.JOB_06)
      Then("the response should contain the following details")
      val expectedResponse: PostEnquiryResponse =
        PostEnquiryResponse(message = "Job successfully submitted")
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }

  }

}
