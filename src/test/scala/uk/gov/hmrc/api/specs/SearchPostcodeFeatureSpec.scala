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
    expectedRecord: List[Record]
  ): Unit = {

    val actualResponseBody: Option[PostcodeSearchResult] =
      context.responseBody

    context.status shouldBe 200

    actualResponseBody should not be empty

    val results = actualResponseBody.get.results

    results.current_page  shouldBe Some(1)
    results.page_size     shouldBe Some(20)
    results.total_results shouldBe Some(10)
    results.total_pages   shouldBe Some(1)
    results.has_next      shouldBe Some(false)
    results.has_previous  shouldBe Some(false)

    results.records shouldBe expectedRecord
  }

  Feature("Search Postcode API Test") {
    Scenario("Search postcode status response") { context =>
      val personForeignId = "123456789567"

      When(s"the get request is sent to the search postcode api with $personForeignId")
      searchPostcode(context, personForeignId)

      Then("the response should contain the expected search result details")

      val expectedRecord: List[Record] =
        List(
          Record(
            ValuationList(
              Id(Some("123456789567")),
              Classification(Some("CVW"), Some("Council tax valuation list for a billing authority in Wales")),
              Some(Country(Some("W92000004"), Some("Wales"))),
              CollectionAuthority(Some("W07000064"), None),
              None,
              None
            ),
            ListEntry(
              None,
              None,
              None,
              Some(Use(Some("General Commercial Use"), None, None)),
              Valuation(Some("E"), None, None),
              Some(Period(Some("20050401T000000Z"), Some("20261004T100000Z"))),
              None,
              None,
              Some(
                Property(
                  Some(Id(Some("192FDEEA-8278-4EC5-870E-19C15030D007"))),
                  None,
                  Some(Address(Some("1, Y DERI DUON, Cardiff, CF14 0AA"), None, None, None)),
                  None
                )
              )
            )
          ),
          Record(
            ValuationList(
              Id(Some("123456789567")),
              Classification(Some("CVW"), Some("Council tax valuation list for a billing authority in Wales")),
              Some(Country(Some("W92000004"), Some("Wales"))),
              CollectionAuthority(Some("W07000064"), None),
              None,
              None
            ),
            ListEntry(
              None,
              None,
              None,
              Some(Use(Some("General Commercial Use"), None, None)),
              Valuation(Some("D"), None, None),
              Some(Period(Some("20050401T000000Z"), Some("20280101T000000Z"))),
              None,
              None,
              Some(
                Property(
                  Some(Id(Some("5D9EDEED-B632-4F1A-BEC9-CF8822B1B7F4"))),
                  None,
                  Some(Address(Some("10, Y DERI DUON, Cardiff, CF14 0AA"), None, None, None)),
                  None
                )
              )
            )
          ),
          Record(
            ValuationList(
              Id(Some("123456789567")),
              Classification(Some("CVW"), Some("Council tax valuation list for a billing authority in Wales")),
              Some(Country(Some("W92000004"), Some("Wales"))),
              CollectionAuthority(Some("W07000064"), None),
              None,
              None
            ),
            ListEntry(
              None,
              None,
              None,
              Some(Use(Some("General Commercial Use"), None, None)),
              Valuation(Some("D"), None, None),
              Some(Period(Some("20050401T000000Z"), Some("20261004T100000Z"))),
              None,
              None,
              Some(
                Property(
                  Some(Id(Some("15B87745-3FB2-40F2-9529-87965CB08D73"))),
                  None,
                  Some(Address(Some("2, Y DERI DUON, Cardiff, CF14 0AA"), None, None, None)),
                  None
                )
              )
            )
          ),
          Record(
            ValuationList(
              Id(Some("123456789567")),
              Classification(Some("CVW"), Some("Council tax valuation list for a billing authority in Wales")),
              Some(Country(Some("W92000004"), Some("Wales"))),
              CollectionAuthority(Some("W07000064"), None),
              None,
              None
            ),
            ListEntry(
              None,
              None,
              None,
              Some(Use(Some("General Commercial Use"), None, None)),
              Valuation(Some("E"), None, None),
              Some(Period(Some("20050401T000000Z"), Some("20280101T000000Z"))),
              None,
              None,
              Some(
                Property(
                  Some(Id(Some("2773E005-88A6-4CFF-949C-ABBC5DF52B53"))),
                  None,
                  Some(Address(Some("3, Y DERI DUON, Cardiff, CF14 0AA"), None, None, None)),
                  None
                )
              )
            )
          ),
          Record(
            ValuationList(
              Id(Some("123456789567")),
              Classification(Some("CVW"), Some("Council tax valuation list for a billing authority in Wales")),
              Some(Country(Some("W92000004"), Some("Wales"))),
              CollectionAuthority(Some("W07000064"), None),
              None,
              None
            ),
            ListEntry(
              None,
              None,
              None,
              Some(Use(Some("General Commercial Use"), None, None)),
              Valuation(Some("D"), None, None),
              Some(Period(Some("20050401T000000Z"), Some("20280101T000000Z"))),
              None,
              None,
              Some(
                Property(
                  Some(Id(Some("431568D2-A99C-4F11-BAAC-FAE982236CEA"))),
                  None,
                  Some(Address(Some("4, Y DERI DUON, Cardiff, CF14 0AA"), None, None, None)),
                  None
                )
              )
            )
          ),
          Record(
            ValuationList(
              Id(Some("123456789567")),
              Classification(Some("CVW"), Some("Council tax valuation list for a billing authority in Wales")),
              Some(Country(Some("W92000004"), Some("Wales"))),
              CollectionAuthority(Some("W07000064"), None),
              None,
              None
            ),
            ListEntry(
              None,
              None,
              None,
              Some(Use(Some("General Commercial Use"), None, None)),
              Valuation(Some("E"), None, None),
              Some(Period(Some("20050401T000000Z"), Some("20280101T000000Z"))),
              None,
              None,
              Some(
                Property(
                  Some(Id(Some("76541CD2-C419-4800-B599-2754C167499F"))),
                  None,
                  Some(Address(Some("5, Y DERI DUON, Cardiff, CF14 0AA"), None, None, None)),
                  None
                )
              )
            )
          ),
          Record(
            ValuationList(
              Id(Some("123456789567")),
              Classification(Some("CVW"), Some("Council tax valuation list for a billing authority in Wales")),
              Some(Country(Some("W92000004"), Some("Wales"))),
              CollectionAuthority(Some("W07000064"), None),
              None,
              None
            ),
            ListEntry(
              None,
              None,
              None,
              Some(Use(Some("General Commercial Use"), None, None)),
              Valuation(Some("D"), None, None),
              Some(Period(Some("20050401T000000Z"), Some("20280101T000000Z"))),
              None,
              None,
              Some(
                Property(
                  Some(Id(Some("6B947036-82AA-4002-9EF7-7D354B228F94"))),
                  None,
                  Some(Address(Some("6, Y DERI DUON, Cardiff, CF14 0AA"), None, None, None)),
                  None
                )
              )
            )
          ),
          Record(
            ValuationList(
              Id(Some("123456789567")),
              Classification(Some("CVW"), Some("Council tax valuation list for a billing authority in Wales")),
              Some(Country(Some("W92000004"), Some("Wales"))),
              CollectionAuthority(Some("W07000064"), None),
              None,
              None
            ),
            ListEntry(
              None,
              None,
              None,
              Some(Use(Some("General Commercial Use"), None, None)),
              Valuation(Some("E"), None, None),
              Some(Period(Some("20050401T000000Z"), Some("20280101T000000Z"))),
              None,
              None,
              Some(
                Property(
                  Some(Id(Some("7D4BE0C3-4898-4C88-9578-F45E41E382E1"))),
                  None,
                  Some(Address(Some("7, Y DERI DUON, Cardiff, CF14 0AA"), None, None, None)),
                  None
                )
              )
            )
          ),
          Record(
            ValuationList(
              Id(Some("123456789567")),
              Classification(Some("CVW"), Some("Council tax valuation list for a billing authority in Wales")),
              Some(Country(Some("W92000004"), Some("Wales"))),
              CollectionAuthority(Some("W07000064"), None),
              None,
              None
            ),
            ListEntry(
              None,
              None,
              None,
              Some(Use(Some("General Commercial Use"), None, None)),
              Valuation(Some("D"), None, None),
              Some(Period(Some("20050401T000000Z"), Some("20280101T000000Z"))),
              None,
              None,
              Some(
                Property(
                  Some(Id(Some("8C230ADA-4973-461E-8163-70D0B7A42DC8"))),
                  None,
                  Some(Address(Some("8, Y DERI DUON, Cardiff, CF14 0AA"), None, None, None)),
                  None
                )
              )
            )
          ),
          Record(
            ValuationList(
              Id(Some("123456789567")),
              Classification(Some("CVW"), Some("Council tax valuation list for a billing authority in Wales")),
              Some(Country(Some("W92000004"), Some("Wales"))),
              CollectionAuthority(Some("W07000064"), None),
              None,
              None
            ),
            ListEntry(
              None,
              None,
              None,
              Some(Use(Some("General Commercial Use"), None, None)),
              Valuation(Some("E"), None, None),
              Some(Period(Some("20050401T000000Z"), Some("20280101T000000Z"))),
              None,
              None,
              Some(
                Property(
                  Some(Id(Some("3E48242B-4AAF-4E3F-87B6-ADA771D65EFE"))),
                  None,
                  Some(Address(Some("9, Y DERI DUON, Cardiff, CF14 0AA"), None, None, None)),
                  None
                )
              )
            )
          )
        )
      theResponseShouldContainSearchResultDetails(
        context,
        expectedRecord
      )
    }
  }
}
