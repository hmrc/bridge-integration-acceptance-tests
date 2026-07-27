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

      val emptyRecord: Record =
        Record(
          list = ValuationList(
            id = None,
            classification = Classification(None, None),
            collection_authority = CollectionAuthority(None, None)
          ),
          list_entry = ListEntry(
            relevant_property = RelevantProperty(None),
            addresses = Addresses(None),
            valuation = Valuation(None)
          )
        )

      val ceredigionCollectionAuthority: CollectionAuthority =
        CollectionAuthority(
          ons_code = Some("W07000064"),
          ons_code_label = Some("Ceredigion | Ceredigion")
        )

      val ceredigionEmptyRecord: Record =
        Record(
          list = ValuationList(
            id = None,
            classification = Classification(None, None),
            collection_authority = ceredigionCollectionAuthority
          ),
          list_entry = ListEntry(
            relevant_property = RelevantProperty(None),
            addresses = Addresses(None),
            valuation = Valuation(None)
          )
        )

      val populatedCeredigionRecord: Record =
        Record(
          list = ValuationList(
            id = None,
            classification = Classification(None, None),
            collection_authority = ceredigionCollectionAuthority
          ),
          list_entry = ListEntry(
            relevant_property = RelevantProperty(Some(3L)),
            addresses = Addresses(None),
            valuation = Valuation(Some("D"))
          )
        )

      val expectedRecords: List[Record] =
        List.fill(8)(emptyRecord) ++
          List(populatedCeredigionRecord) ++
          List.fill(39)(ceredigionEmptyRecord)

      val expectedResponse: PostcodeSearchResult =
        PostcodeSearchResult(
          results = Results(
            current_page = None,
            page_size = None,
            total_results = None,
            total_pages = None,
            has_next = None,
            has_previous = None,
            self = None,
            next = None,
            prev = None,
            first = None,
            last = None,
            records = expectedRecords
          )
        )

      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }
  }
}
