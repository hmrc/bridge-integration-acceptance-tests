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
    with CreateCTEnquiryStepHelper{

  override type FixtureParam = EnquiryContext

  override def withFixture(test: OneArgTest): Outcome = {
    val context = EnquiryContext()
    try test(context)
    finally ()
  }


  Feature("POST CT Enquiry  API Test") {

    Scenario("J09 ChangesToTheLocalArea AffectedMyProperty POST Enquiry") { context =>
      val personForeignId = "123456789567"
      When(s"the get request is sent to the dashboard api with $personForeignId")
      createCTEnquiry(context, personForeignId, EnquiryJobName.JOB_09)
      Then("the response should contain the following details")
      val expectedResponse: PostEnquiryResponse =
        PostEnquiryResponse(message = "Job successfully submitted")
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }

  }

}
