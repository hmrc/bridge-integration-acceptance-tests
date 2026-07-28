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
import models.search.{PostcodeSearchResult, Record}
import models.search.*
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

  Feature("Search Postcode API Test") {
    Scenario("Search postcode status response") { context =>
      val personForeignId = "123456789567"

      When(s"the get request is sent to the search postcode api with $personForeignId")
      searchPostcode(context, personForeignId)

      Then("the response should contain the following details")

      val expectedRecord: Record =
        Record(
          list = ValuationList(
            id = Id(Some("123456789567")),
            classification = Classification(
              code = Some(""),
              meaning = Some("")
            ),
            collection_authority = CollectionAuthority(
              ons_code = Some("W07000064"),
              ons_code_label = Some("Ceredigion | Ceredigion")
            )
          ),
          list_entry = ListEntry(
            relevant_property = RelevantProperty(
              vos_property_id = Some("VOS-2")
            ),
            addresses = Addresses(
              property_full_address = Some("4 Clos y Fedwen, Cardiff, CF14 0AA")
            ),
            valuation = Valuation(
              value = Some("D")
            )
          )
        )

      val expectedResponse: PostcodeSearchResult =
        PostcodeSearchResult(
          results = Results(
            current_page = Some(1),
            page_size = Some(20),
            total_results = Some(1),
            total_pages = Some(1),
            has_next = Some(false),
            has_previous = Some(false),
            self = None,
            next = None,
            prev = None,
            first = None,
            last = None,
            records = Seq(expectedRecord)
          )
        )

      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }
  }
}
