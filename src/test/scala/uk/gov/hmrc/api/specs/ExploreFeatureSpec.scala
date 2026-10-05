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
          ExploreResults(
            List(
              ExploreRecord(
                ExploreData(
                  ValuationList(
                    Id(Some("123456789567")),
                    Classification(Some("CVW"), Some("Council tax valuation list for a billing authority in Wales")),
                    Some(Country(Some("W92000004"), Some("Wales"))),
                    CollectionAuthority(Some("W07000064"), None),
                    Some(InforcementPeriod(Some("20050401"), None)),
                    Some(ListAdministration(Some("20261005T100000Z"), Some("20261005T100000Z"), Some("1001")))
                  ),
                  ListEntry(
                    Some(Id(Some("123456789567"))),
                    Some(DesignatedPerson(None, None, None)),
                    None,
                    Some(Use(Some("General Commercial Use"), Some("N"), Some("N"))),
                    Valuation(Some("E"), Some(Method(Some("W07000064"), None)), None),
                    Some(Period(Some("20050401T000000Z"), Some("20261004T100000Z"))),
                    Some(
                      Administration(
                        Some("20261005T100000Z"),
                        Some("1"),
                        Some("1"),
                        Some("V"),
                        Some("Not Transitionally"),
                        None
                      )
                    ),
                    Some(Workflow(Some("R5R875-B52D043-F767863-66ZZZ"))),
                    Some(
                      Property(
                        Some(Id(Some("192FDEEA-8278-4EC5-870E-19C15030D007"))),
                        Some("273996000001"),
                        Some(Address(Some("1, Y DERI DUON, Cardiff, CF14 0AA"), Some("1"), Some("CF14 0AA"), None)),
                        Some(PropertyWorkflow(Some("N")))
                      )
                    )
                  )
                )
              )
            )
          )
        )
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }
  }
}
