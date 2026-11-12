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
    results.total_results shouldBe Some(50)
    results.total_pages   shouldBe Some(3)
    results.has_next      shouldBe Some(true)
    results.has_previous  shouldBe Some(false)

    results.records shouldBe expectedRecord
  }

  Feature("Search Postcode API Test") {
    Scenario("Search postcode status response") { context =>
      val personForeignId = "123456789567"

      When(s"the get request is sent to the search postcode api with $personForeignId")
      searchPostcode(context, personForeignId)

      Then("the response should contain the expected search result details")

      def expectedRecord(
        listEntryId: String,
        valuation: String,
        propertyId: String,
        address: String
      ): Record =
        Record(
          ValuationList(
            Id(Some("1234567895657")),
            Classification(
              Some("CVW"),
              Some("Council tax valuation list for a billing authority in Wales")
            ),
            Some(
              Country(
                Some("W92000005"),
                Some("Wales")
              )
            ),
            CollectionAuthority(
              Some("W07000065"),
              Some("Ceredigion")
            ),
            None,
            None
          ),
          ListEntry(
            Some(Id(Some(listEntryId))),
            None,
            None,
            Some(
              Use(
                Some("Domestic Use"),
                None,
                None
              )
            ),
            Valuation(
              Some(valuation),
              None,
              None
            ),
            Some(
              Period(
                Some("20230401T000000Z"),
                None
              )
            ),
            None,
            None,
            Some(
              Property(
                Some(Id(Some(propertyId))),
                None,
                Some(
                  Address(
                    Some(address),
                    None,
                    None,
                    None
                  )
                ),
                None
              )
            )
          )
        )

      val expectedRecords: List[Record] =
        List(
          expectedRecord(
            "D31AD68E-1459-4D59-A320-60C68A924D8E",
            "Deleted",
            "E1C615B2-962D-4A91-A8AA-4BA87EF641D3",
            "1, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "05957B85-C96B-4DE1-81EA-6E07A48D445E",
            "Deleted",
            "27FB8955-9B35-443E-8D8F-84E0E012413E",
            "2, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "5B8045CB-039F-4926-B3FC-1578D9C436D9",
            "E",
            "7F05E4AF-4663-425C-8D99-94CC07B11D5F",
            "3, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "BF7B06F8-BC8E-49D5-A4F2-C215A695DC86",
            "E",
            "D1EF1D1E-0A7D-4CB6-AA61-B3D070C7F3E6",
            "4, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "94C5414C-1A19-46E7-9EBD-37F026DDD131",
            "E",
            "34E41E24-222B-4DAD-9FF7-F0DD004631DF",
            "5, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "4416150B-5B4D-4AB8-9902-35CAD10B3F3D",
            "E",
            "8A32C2B7-E3E1-4C9B-BEEC-923D02ADEC5C",
            "6, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "DB5D3FCB-911F-40F6-B061-77D1ADF447E3",
            "E",
            "547779C2-6F41-41CD-A74E-29B68D2D5E3C",
            "7, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "E8D09761-0ECD-44BE-AE42-3D319D579362",
            "E",
            "88D55DCE-BB85-4291-B3DD-E081ABB0EEEC",
            "8, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "A52ED4E4-8E71-4F9A-89C5-0F811BCE1D80",
            "E",
            "6CA38874-E290-4925-964D-6D2297D3892F",
            "9, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "BC36A502-6E98-4611-BAE1-58C084723E89",
            "E",
            "DDABA689-461A-42C7-955C-BD9E8700A7C5",
            "10, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "1C178919-4951-4D81-A406-3A1989568CE8",
            "E",
            "FC3FCF20-D205-4125-9E45-AB7D829DD232",
            "11, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "B9BDC22F-3F98-4D97-9D89-79F0F2B2077F",
            "E",
            "8A36D2DF-21C7-4EDC-A0A2-641D037F85D8",
            "12, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "4D9997D6-94E9-481E-8C76-6A66A4020536",
            "E",
            "990FB48E-F626-4E15-8ECC-2BE077F7B600",
            "13, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "187C059B-59AF-446D-9913-35B9FB8BB378",
            "E",
            "0256D2D2-4E78-4DEF-B77A-E41AF75D0862",
            "14, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "4F503F53-4B2B-456B-8508-AE4D89BA2571",
            "E",
            "F771505C-13D9-4D6D-BBB3-6638626D1880",
            "15, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "14E00E13-B91C-492D-860D-B3DDF4B65303",
            "E",
            "A2636852-8E62-4A59-9ADC-1797086ECECA",
            "16, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "313AB405-E1DD-41D8-9615-08A2C53C4D28",
            "E",
            "2C733050-86F3-4B5A-A544-1B7E3D8FAC01",
            "17, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "67FAC66D-8EFB-427A-8A67-9B1C016D878E",
            "E",
            "58F1FAFA-ADA6-42AA-8AE0-C889DEB5E66B",
            "18, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "C14323CB-9CD6-4403-BA00-2EBEB4FD22F3",
            "E",
            "A985BF7E-90E4-41E7-B942-7295B580074E",
            "19, Y DERI DUON, Cardiff, CF14 0AA"
          ),
          expectedRecord(
            "4F2117E5-88A4-43DC-A716-68330B156946",
            "E",
            "754465C3-2579-41A4-A46F-0538696ADB87",
            "20, Y DERI DUON, Cardiff, CF14 0AA"
          )
        )
      theResponseShouldContainSearchResultDetails(
        context,
        expectedRecords
      )
    }
  }
}
