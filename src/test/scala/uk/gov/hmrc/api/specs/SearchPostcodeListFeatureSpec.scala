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

import steps.context.SearchPostcodeListContext

import steps.helpers.SearchPostcodeListStepHelper

class SearchPostcodeListFeatureSpec
    extends FixtureAnyFeatureSpec
    with GivenWhenThen
    with Matchers
    with SearchPostcodeListStepHelper {

  override type FixtureParam = SearchPostcodeListContext

  override def withFixture(test: OneArgTest): Outcome = {

    val context = SearchPostcodeListContext()

    try test(context)

    finally ()

  }

  private def theResponseShouldContainSearchResultDetails(
    context: FixtureParam,
    expectedTotalResults: Int = 50
  ): Unit = {

    val actualResponseBody =
      context.responseBody

    context.status shouldBe 200

    actualResponseBody should not be empty

    val results = actualResponseBody.get.results

    results.current_page shouldBe Some(1)

    results.page_size shouldBe Some(20)

    results.total_results shouldBe Some(expectedTotalResults)

    results.total_pages shouldBe Some((expectedTotalResults + 19) / 20)

    results.has_next shouldBe Some(expectedTotalResults > 20)

    results.has_previous shouldBe Some(false)

  }

  Feature("Search Postcode By List Type API Test") {

    Scenario("Bridge-BS-013: Search using Full postcode") { context =>
      val personForeignId = "123456789567"
      val request         = PostcodeSearchRequest(postcode = "CF140AA", listType = "CVW")

      When(s"the get request is sent to search?postcode=${request.postcode}&listType=${request.listType}")
      searchPostcode(context, personForeignId, request)

      Then("the response should contain the expected search result details")
      theResponseShouldContainSearchResultDetails(context)
    }

    Scenario("Bridge-BS-014: Search using Partial postcode") { context =>
      val personForeignId = "123456789567"
      val request         = PostcodeSearchRequest(postcode = "CF14", listType = "CVW")

      When(s"the get request is sent to search?postcode=${request.postcode}&listType=${request.listType}")
      searchPostcode(context, personForeignId, request)

      Then("the response should contain the expected search result details")
      theResponseShouldContainSearchResultDetails(context)
    }

    Scenario("Bridge-BS-015: Advanced Search using Full Postcode & Property Name/Number") { context =>
      val personForeignId = "123456789567"
      val request         = PostcodeSearchRequest(postcode = "CF140AA", listType = "CVW", propertyName = Some("1"))

      When(
        s"the get request is sent to search?postcode=${request.postcode}&listType=${request.listType}&propertyName=${request.propertyName.get}"
      )
      searchPostcode(context, personForeignId, request)

      Then("the response should contain the expected search result details")
      theResponseShouldContainSearchResultDetails(context, expectedTotalResults = 14)
    }

    Scenario("Bridge-BS-016: Advanced Search using Full Postcode & Street") { context =>
      val personForeignId = "123456789567"
      val request         = PostcodeSearchRequest(postcode = "CF140AA", listType = "CVW", street = Some("TY GLAS ROAD"))

      When(
        s"the get request is sent to search?postcode=${request.postcode}&listType=${request.listType}&street=${request.street.get}"
      )
      searchPostcode(context, personForeignId, request)

      Then("the response should contain the expected search result details")
      theResponseShouldContainSearchResultDetails(context, expectedTotalResults = 0)
    }

    Scenario("Bridge-BS-017: Advanced Search using Partial Postcode & Property Name/Number") { context =>
      val personForeignId = "123456789567"
      val request         = PostcodeSearchRequest(postcode = "CF14", listType = "CVW", propertyName = Some("1"))

      When(
        s"the get request is sent to search?postcode=${request.postcode}&listType=${request.listType}&propertyName=${request.propertyName.get}"
      )
      searchPostcode(context, personForeignId, request)

      Then("the response should contain the expected search result details")
      theResponseShouldContainSearchResultDetails(context, expectedTotalResults = 14)
    }

    Scenario("Bridge-BS-018: Advanced Search using Partial Postcode & Street") { context =>
      val personForeignId = "123456789567"
      val request         = PostcodeSearchRequest(postcode = "CF14", listType = "CVW", street = Some("TY GLAS ROAD"))

      When(
        s"the get request is sent to search?postcode=${request.postcode}&listType=${request.listType}&street=${request.street.get}"
      )
      searchPostcode(context, personForeignId, request)

      Then("the response should contain the expected search result details")
      theResponseShouldContainSearchResultDetails(context, expectedTotalResults = 0)
    }

    Scenario("Bridge-BS-019: Advanced Search using Partial Postcode & Town/City") { context =>
      val personForeignId = "123456789567"
      val request         = PostcodeSearchRequest(postcode = "CF14", listType = "CVW", town = Some("CARDIFF"))

      When(
        s"the get request is sent to search?postcode=${request.postcode}&listType=${request.listType}&town=${request.town.get}"
      )
      searchPostcode(context, personForeignId, request)

      Then("the response should contain the expected search result details")
      theResponseShouldContainSearchResultDetails(context)
    }

    Scenario("Bridge-BS-020: Advanced Search using Full Postcode & Full property Details") { context =>
      val personForeignId = "123456789567"
      val request         = PostcodeSearchRequest(
        postcode = "CF140AA",
        listType = "CVW",
        propertyName = Some("1"),
        street = Some("TY GLAS ROAD"),
        town = Some("CARDIFF")
      )

      When(
        s"the get request is sent to search?postcode=${request.postcode}&listType=${request.listType}&propertyName=${request.propertyName.get}&street=${request.street.get}&town=${request.town.get}"
      )
      searchPostcode(context, personForeignId, request)

      Then("the response should contain the expected search result details")
      theResponseShouldContainSearchResultDetails(context, expectedTotalResults = 0)
    }

    Scenario("Bridge-BS-021: Advanced Search using Partial Postcode & Full property Details") { context =>
      val personForeignId = "123456789567"
      val request         = PostcodeSearchRequest(
        postcode = "CF14",
        listType = "CVW",
        propertyName = Some("1"),
        street = Some("TY GLAS ROAD"),
        town = Some("CARDIFF")
      )

      When(
        s"the get request is sent to search?postcode=${request.postcode}&listType=${request.listType}&propertyName=${request.propertyName.get}&street=${request.street.get}&town=${request.town.get}"
      )
      searchPostcode(context, personForeignId, request)

      Then("the response should contain the expected search result details")
      theResponseShouldContainSearchResultDetails(context, expectedTotalResults = 0)
    }

    Scenario("Bridge-BS-022: Advanced Search using Full Postcode & any 2 property details") { context =>
      val personForeignId = "123456789567"
      val request         = PostcodeSearchRequest(
        postcode = "CF140AA",
        listType = "CVW",
        propertyName = Some("1"),
        town = Some("CARDIFF")
      )

      When(
        s"the get request is sent to search?postcode=${request.postcode}&listType=${request.listType}&propertyName=${request.propertyName.get}&town=${request.town.get}"
      )
      searchPostcode(context, personForeignId, request)

      Then("the response should contain the expected search result details")
      theResponseShouldContainSearchResultDetails(context, expectedTotalResults = 14)
    }

  }

}
