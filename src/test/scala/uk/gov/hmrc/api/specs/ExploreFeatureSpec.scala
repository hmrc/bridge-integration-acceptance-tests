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
import models.search.{ExploreResult, Record, *}
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
          list = ValuationList(
            id = Id(Some("123456789567")),
            classification = Classification(
              code = Some(""),
              meaning = Some("")
            ),
            country = Some(
              Country(
                ons_code = Some("W92000004"),
                ons_code_label = Some("Wales | Cymru")
              )
            ),
            collection_authority = CollectionAuthority(
              ons_code = Some("W07000064"),
              ons_code_label = Some("Ceredigion | Ceredigion")
            ),
            inforcement_period = Some(
              InforcementPeriod(
                commencement_date = Some("20050401"),
                expiration_date = None
              )
            ),
            compilation_date = None,
            valuation_date = None,
            total_of_all_valuations = None
          ),
          list_entry = ListEntry(
            id = Some(
              Id(
                value = Some("123456789567")
              )
            ),
            designated_person = Some(
              DesignatedPerson(
                name = Some("Designated Person 2"),
                address = Some("1 Test Street, London"),
                company_number = Some("COMP2")
              )
            ),
            relevant_property = Some(
              RelevantProperty(
                vos_property_id = Some("VOS-2")
              )
            ),
            use = Some(
              Use(
                description = Some("General Commercial Use"),
                composite_ind = Some("N"),
                part_exempt_ind = Some("N")
              )
            ),
            valuation = Valuation(
              value = Some("D"),
              method = Some(
                Method(
                  code = None,
                  meaning = None
                )
              ),
              previous = Some("STD")
            ),
            period = Some(
              Period(
                effective_from_date = Some("20050401"),
                effective_to_date = None
              )
            ),
            administration = Some(
              Administration(
                alteration_date = Some("20230401"),
                alteration_seq_no = Some("1"),
                entry_seq_no = Some("1"),
                judicially_ordered_by = None,
                transitionally_certified = Some("N"),
                collection_authority_ref = None
              )
            ),
            workflow = Some(
              Workflow(
                creating_job_id = Some("R5R875-B52D043-F767863-66ZZZ")
              )
            ),
            addresses = Addresses(
              property_full_address = Some("4 Clos y Fedwen, Cardiff, CF14 0AA")
            ),
            property = Some(
              Property(
                improvement_ind = None
              )
            )
          )
        )

      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }
  }
}
