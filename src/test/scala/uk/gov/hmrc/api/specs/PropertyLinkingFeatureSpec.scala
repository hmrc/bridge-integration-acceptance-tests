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

import models.{AddressData, CodeMeaning, Communications, ForeignId, ListData, LocationData, Metadata, MetadataStage, NameData, Person, PersonItem, PersonItemData, Persons, Properties, Property, PropertyAssessment, PropertyAssessmentData, PropertyData, PropertyLinkingResponse, PropertyReference, PropertyUse, RatepayerStatusResponse, ReceivingMetadata, RegisterRatepayerResponse, SendingMetadata, ValuationData, WorkflowData}
import org.scalatest.featurespec.FixtureAnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{GivenWhenThen, Outcome}
import steps.context.{PropertyLinkingContext, RegisterContext}
import steps.helpers.{PropertyLinkingStepHelper, RegisterRatepayerStepHelper}

class PropertyLinkingFeatureSpec
    extends FixtureAnyFeatureSpec
    with GivenWhenThen
    with Matchers
    with PropertyLinkingStepHelper {

  override type FixtureParam = PropertyLinkingContext

  override def withFixture(test: OneArgTest): Outcome = {
    val context = PropertyLinkingContext()
    try test(context)
    finally ()
  }

  Feature("PropertyLinking API Test") {

    Scenario("PropertyLinking Response") { context =>
      val personForeignId              = "123456789567"
      val assessmentId                 = "27399677000"
      When(s"the get request is sent to the property linking api with $personForeignId and $assessmentId")
      propertyLinking(context, personForeignId, assessmentId)
      Then("the response should contain the following details")
      val expectedResponse: Properties = Properties(
        properties = List(
          Property(
            id = 16L,
            idx = "1.1.1",
            name = "Person 1",
            label = "Person Label 1",
            description =
              "A Government Gateway authenticated natural, corporate or crown user of online services provided by the UK Government/HMRC whose type and class are not yet known or re-confirmed.",
            origination = "20260407T111712Z",
            termination = None,
            category = CodeMeaning(
              Some("LTX-DOM-PSN"),
              Some("Local taxation domain person")
            ),
            `type` = CodeMeaning(
              Some("COR"),
              Some("Corporate")
            ),
            `class` = CodeMeaning(
              Some("GGY"),
              Some("Any government gateway user")
            ),
            data = PropertyData(
              foreign_ids = List(
                ForeignId(
                  system = Some("Government_Gateway"),
                  location = Some("UK"),
                  value = Some("123456789567")
                )
              ),
              foreign_names = List.empty,
              foreign_labels = List.empty,
              addresses = AddressData(
                None,
                None,
                None,
                None
              ),
              location = LocationData(
                None,
                None,
                None
              ),
              assessments = List(
                PropertyAssessment(
                  id = 13L,
                  idx = "1.2.1.1",
                  name = "Persona 1",
                  label = "Persona Label 1",
                  description =
                    "A local taxation domain authenticated persona whose type and class are not yet known or re-confirmed, and that is assignable to the person.",
                  origination = "20260407T111712Z",
                  termination = None,
                  CodeMeaning(
                    Some("LTX-DOM-PSA"),
                    Some("Local taxation domain persona")
                  ),
                  `type` = CodeMeaning(
                    Some("TXP"),
                    Some("LGFA taxpayer")
                  ),
                  `class` = CodeMeaning(
                    Some("RPO"),
                    Some("Ratepayer (occupier)")
                  ),
                  data = PropertyAssessmentData(
                    foreign_ids = List(
                      ForeignId(
                        system = Some("Government_Gateway"),
                        location = Some("UK"),
                        value = Some("123456789567")
                      )
                    ),
                    foreign_names = List.empty,
                    foreign_labels = List.empty,
                    property = PropertyReference(
                      property_id = 123L,
                      cdb_property_id = 123L
                    ),
                    use = PropertyUse(
                      is_composite = Some(true),
                      is_part_exempt = Some(true),
                      use_description = Some("test")
                    ),
                    valuation_surveys = List.empty,
                    valuations = List.empty,
                    valuation = ValuationData(
                      valuation_method_code = Some("123"),
                      valuation_rateable = Some(23L),
                      valuation_effective_date = Some("test")
                    ),
                    list = ListData(
                      list_category = None,
                      list_function = None,
                      list_year = None,
                      list_authority_code = None
                    ),
                    workflow = WorkflowData(
                      cdb_job_id = None
                    )
                  ),
                  protodata = List.empty,
                  metadata = Metadata(
                    sending = SendingMetadata(
                      extracting = MetadataStage(selecting = Map.empty),
                      transforming = MetadataStage(
                        filtering = Map.empty,
                        supplementing = Map.empty,
                        recontextualising = Map.empty
                      ),
                      loading = MetadataStage()
                    ),
                    receiving = ReceivingMetadata(
                      unloading = MetadataStage(),
                      transforming = MetadataStage(),
                      storing = MetadataStage()
                    )
                  ),
                  compartments = Map.empty,
                  items = List.empty
                )
              )
            ),
            protodata = List.empty,
            metadata = Metadata(
              sending = SendingMetadata(
                extracting = MetadataStage(selecting = Map.empty),
                transforming = MetadataStage(
                  filtering = Map.empty,
                  supplementing = Map.empty,
                  recontextualising = Map.empty
                ),
                loading = MetadataStage()
              ),
              receiving = ReceivingMetadata(
                unloading = MetadataStage(),
                transforming = MetadataStage(),
                storing = MetadataStage()
              )
            ),
            compartments = Map.empty,
            items = List.empty
          )
        )
      )

      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }

  }

}
