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
import org.scalatest.{GivenWhenThen, Outcome}
import org.scalatest.featurespec.FixtureAnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{GivenWhenThen, Outcome}
import steps.context.SearchPostcodeContext
import steps.helpers.SearchPostcodeStepHelper

class SearchPostcodeFeatureSpec
    extends FixtureAnyFeatureSpec
    with GivenWhenThen
    with Matchers
    with SearchPostcodeStepHelper {

  override type FixtureParam = SearchPostcodeContext

  override def withFixture(test: OneArgTest): Outcome = {
    val context = SearchPostcodeContext()
    try test(context)
    finally ()
  }

  private def theResponseShouldContainSearchResultDetails(
    context: FixtureParam,
    expectedRecord: Record
  ): Unit = {

    val actualResponseBody: Option[PostcodeSearchResult] =
      context.responseBody

    context.status shouldBe 200

    actualResponseBody should not be empty

    val results = actualResponseBody.get.results

    results.current_page  shouldBe Some(1)
    results.page_size     shouldBe Some(20)
    results.total_results shouldBe Some(1)
    results.total_pages   shouldBe Some(1)
    results.has_next      shouldBe Some(false)
    results.has_previous  shouldBe Some(false)

    results.records should contain(expectedRecord)
  }

  Feature("Search Postcode API Test") {
    Scenario("Search postcode status response") { context =>
      val personForeignId = "123456789567"

      When(s"the get request is sent to the search postcode api with $personForeignId")
      searchPostcode(context, personForeignId)

      Then("the response should contain the expected search result details")

      val expectedRecord: Record =
        Record(
          ValuationList(
            Id(Some("123456789567")),
            Classification(
              Some("CVW"),
              Some("Council tax valuation list for a billing authority in Wales (LGFA92s22B2(b)to3A)")
            ),
            None,
            CollectionAuthority(Some("W07000064"), Some("Ceredigion | Ceredigion")),
            None,
            None,
            None,
            None
          ),
          ListEntry(
            None,
            None,
            Some(RelevantProperty(Some("VOS-844"))),
            None,
            Valuation(Some("D"), None, None),
            None,
            None,
            None,
            Addresses(Some("1 Y Deri Duon, Lisvane, Cardiff,CF14 0AA")),
            None
          )
        )
      theResponseShouldContainSearchResultDetails(
        context,
        expectedRecord
      )
    }
  }
}
