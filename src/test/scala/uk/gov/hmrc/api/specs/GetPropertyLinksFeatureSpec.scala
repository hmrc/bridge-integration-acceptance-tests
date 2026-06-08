package uk.gov.hmrc.api.specs

import org.scalatest.{GivenWhenThen, Outcome}
import org.scalatest.featurespec.FixtureAnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import steps.context.GetPropertyLinksContext
import steps.helpers.PropertyLinkingStepHelper

class GetPropertyLinksFeatureSpec
    extends FixtureAnyFeatureSpec
    with GivenWhenThen
    with Matchers
    with PropertyLinkingStepHelper {

  override type FixtureParam = GetPropertyLinksContext

  override def withFixture(test: OneArgTest): Outcome = {
    val context = GetPropertyLinksContext()
    try test(context)
    finally ()
  }

  Feature("Get Property Links API Test") {
    Scenario("Get Property Links Response") { context =>
      val personForeignId = "123456789567"
      When(s"the get request is sent to the property linking api with $personForeignId")
      getPropertyLinks(context, personForeignId)
      Then("the response should contain the following details")
      context.status shouldBe 200
    }
  }
}
