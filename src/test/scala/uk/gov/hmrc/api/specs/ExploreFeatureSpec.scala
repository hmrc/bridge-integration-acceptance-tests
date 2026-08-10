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

import models.search.*
import org.scalatest.featurespec.FixtureAnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{GivenWhenThen, Outcome}
import steps.context.ExploreContext
import steps.helpers.ExploreStepHelper

class ExploreFeatureSpec extends FixtureAnyFeatureSpec with GivenWhenThen with Matchers with ExploreStepHelper {

  override type FixtureParam = ExploreContext

  override def withFixture(test: OneArgTest): Outcome = {
    val context = ExploreContext()
    try test(context)
    finally ()
  }

  Feature("Explore API Test") {
    Scenario("Explore status response") { context =>
      val personForeignId = "123456789567"

      When(s"the get request is sent to the explore api with $personForeignId")
      explore(context, personForeignId)

      Then("the response should contain the following details")

      val expectedResponse: ExploreResult =
        ExploreResult(
          ValuationList(
            Id(Some("123456789567")),
            Classification(
              Some("CVW"),
              Some("Council tax valuation list for a billing authority in Wales (LGFA92s22B2(b)to3A)")
            ),
            Some(Country(Some("W92000004"), Some("Wales | Cymru"))),
            CollectionAuthority(Some("W07000064"), Some("Ceredigion | Ceredigion")),
            Some(InforcementPeriod(Some("20050401"), None)),
            None,
            None,
            None
          ),
          ListEntry(
            Some(Id(Some("123456789567"))),
            Some(DesignatedPerson(Some("Designated Person 844"), Some("1 Test Street, London"), Some("COMP844"))),
            Some(RelevantProperty(Some("VOS-844"))),
            Some(Use(Some("General Commercial Use"), Some("N"), Some("N"))),
            Valuation(Some("D"), Some(Method(None, None)), Some("STD")),
            Some(Period(Some("20050401"), None)),
            Some(Administration(Some("20230401"), Some("1"), Some("1"), None, Some("N"), None)),
            Some(Workflow(Some("R5R875-B52D043-F767863-66ZZZ"))),
            Addresses(Some("1 Y Deri Duon, Lisvane, Cardiff,CF14 0AA")),
            Some(Property(Some("N")))
          )
        )
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }
  }
}
