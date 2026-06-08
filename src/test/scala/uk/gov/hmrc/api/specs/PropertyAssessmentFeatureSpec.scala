package uk.gov.hmrc.api.specs

import models.PropertyAssessmentResponse
import org.scalatest.{GivenWhenThen, Outcome}
import org.scalatest.featurespec.FixtureAnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import steps.context.PropertyAssessmentContext
import steps.helpers.PropertyAssessmentStepHelper

class PropertyAssessmentFeatureSpec
    extends FixtureAnyFeatureSpec
    with GivenWhenThen
    with Matchers
    with PropertyAssessmentStepHelper {

  override type FixtureParam = PropertyAssessmentContext

  override def withFixture(test: OneArgTest): Outcome = {
    val context = PropertyAssessmentContext()
    try test(context)
    finally ()
  }

  Feature("POST Property Assessment") {
    Scenario("Property Assessment Response") { context =>
      val personForeignId  = "123456789567"
      val assessmentId     = "27399677000"
      When(s"the get request is sent to the property linking api with $personForeignId and $assessmentId")
      propertyAssessment(context, personForeignId, assessmentId)
      Then("the response should contain the following details")
      val expectedResponse = PropertyAssessmentResponse("response")
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }
  }

}
