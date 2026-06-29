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

import models.*
import org.scalatest.featurespec.FixtureAnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import org.scalatest.{GivenWhenThen, Outcome}
import steps.context.PropertyAssessmentContext
import steps.helpers.PropertyAssessmentStepHelper

class PropertyAssessmentFeatureSpec
    extends FixtureAnyFeatureSpec
    with GivenWhenThen
    with Matchers
    with PropertyAssessmentStepHelper {

  override type FixtureParam = PropertyAssessmentContext

  override def withFixture(test: OneArgTest): Outcome = {
    val context = PropertyAssessmentContext()
    try test(context)
    finally ()
  }

  private val emptyStage: MetadataStage =
    MetadataStage(
      Map.empty,
      Map.empty,
      Map.empty,
      Map.empty,
      Map.empty,
      Map.empty,
      Map.empty,
      Map.empty,
      Map.empty,
      Map.empty,
      Map.empty,
      Map.empty,
      Map.empty,
      Map.empty,
      Map.empty
    )

  private val emptyMetadata: Metadata =
    Metadata(
      SendingMetadata(
        emptyStage,
        emptyStage,
        emptyStage
      ),
      ReceivingMetadata(
        emptyStage,
        emptyStage,
        emptyStage
      )
    )

  Feature("Property Assessment GET API Test") {

    Scenario("Retrieve a Property Response") { context =>
      val personForeignId = "123456789567"

      When(s"the get request is sent to the property assessment endpoint with $personForeignId")
      propertyAssessment(context, personForeignId)

      Then("the response should contain the following details")

      val expectedSurveyLevelItem: SurveyLevelItem =
        SurveyLevelItem(
          id = 1,
          idx = "1",
          name = None,
          label = "Site",
          description = None,
          origination = None,
          termination = None,
          category = CodeMeaning(
            code = Some("LTX-DOM-CON"),
            meaning = Some(
              "Temporal spatial unit formed by reference to statutory and common law principles of rateable occupation."
            )
          ),
          `type` = CodeMeaning(
            code = Some("HOR"),
            meaning = Some("A two dimensional horizontal unit of space")
          ),
          `class` = CodeMeaning(
            code = Some("SIT"),
            meaning = Some("Site")
          ),
          data = SurveyData(
            artifacts = List(
              ArtifactRecord(
                activity = SourceValue(
                  source = None,
                  value = None
                ),
                code = SourceValue(
                  source = None,
                  value = None
                ),
                description = SourceValue(
                  source = Some("tom:sql/cdb:ndr/plant_machinery/os_refno:24104677000/description"),
                  value = Some("Plant and Machinery (Goods Lift)")
                ),
                quantity = QuantitySourceValue(
                  source = Some("tom:sql/cdb:ndr/plant_machinery/os_refno:24104677000/value"),
                  value = 2550L
                ),
                units = SourceValue(
                  source = None,
                  value = Some("GDP")
                )
              )
            ),
            attributions = List.empty,
            constructions = List.empty,
            facilities = List.empty,
            foreign_ids = List(
              ForeignId(
                system = Some("HMRC-VOA_CDB"),
                location = Some("hmrc/voa/cdb/hereditament_vals"),
                value = Some("24104688000")
              )
            ),
            foreign_labels = List.empty,
            foreign_names = List.empty,
            uninheritances = List.empty,
            uses = List.empty
          ),
          protodata = List.empty,
          metadata = emptyMetadata,
          compartments = Map.empty,
          items = List.empty
        )

      val expectedValuationSurvey: ValuationSurvey =
        ValuationSurvey(
          id = 13,
          idx = "1.13.1",
          name = None,
          label = "Valuation survey supporting a local taxation rating list entry (assessment)",
          description = Some("Valuation Survey"),
          origination = Some("20260407T111712Z"),
          termination = None,
          category = CodeMeaning(
            code = Some("LTX-DOM-VAS"),
            meaning = Some("Local taxation domain valuation survey")
          ),
          `type` = CodeMeaning(
            code = Some("SPL"),
            meaning = Some("Composed of a hierarchy of spatial containers")
          ),
          `class` = CodeMeaning(
            code = Some("NIA"),
            meaning = Some("Areas measured to faces of internal perimeter or party walls")
          ),
          data = ValuationSurveyData(
            foreign_ids = List(
              ForeignId(
                system = Some("CDB_VSA_SURVEY"),
                location = Some("hmrc-voa/cdb/vsa/hereditament_vals"),
                value = Some("24104677000")
              )
            ),
            foreign_names = List.empty,
            foreign_labels = List.empty,
            survey = expectedSurveyLevelItem
          ),
          protodata = List.empty,
          metadata = emptyMetadata,
          compartments = Map.empty,
          items = List.empty
        )

      val expectedAssessment: PropertyAssessment =
        PropertyAssessment(
          id = 13,
          idx = "1.13.1",
          name = None,
          label = "Non Domestic Rating Assessment",
          description = None,
          origination = "20260407T111712Z",
          termination = None,
          category = CodeMeaning(
            code = Some("LTX-DOM-AST"),
            meaning = Some("Local taxation domain assessment")
          ),
          `type` = CodeMeaning(
            code = Some("CHG"),
            meaning = Some("To be determined")
          ),
          `class` = CodeMeaning(
            code = Some("RLE"),
            meaning = Some("Register list entry")
          ),
          data = PropertyAssessmentData(
            foreign_ids = List(
              ForeignId(
                system = Some("HMRC-VOA_CDB"),
                location = Some("hmrc/voa/cdb/ndr_assessments"),
                value = Some("27399677000")
              )
            ),
            foreign_names = List.empty,
            foreign_labels = List.empty,
            property = PropertyReference(
              property_id = 13,
              cdb_property_id = 13
            ),
            use = PropertyUse(
              is_composite = Some("N"),
              is_part_exempt = Some("N"),
              use_description = Some("RESTAURANT AND PREMISES RESTAURANT AND PREMISES")
            ),
            valuation_surveys = List(expectedValuationSurvey),
            valuations = List.empty,
            valuation = ValuationData(
              valuation_method_code = Some("234"),
              valuation_rateable = Some(76500L),
              valuation_effective_date = Some("20260407T111712Z")
            ),
            list = ListData(
              list_category = Some("LTX-DOM-LST"),
              list_function = Some("Charging"),
              list_year = Some("2023"),
              list_authority_code = Some("1160")
            ),
            workflow = WorkflowData(
              cdb_job_id = Some(39115380283L)
            )
          ),
          protodata = List.empty,
          metadata = emptyMetadata,
          compartments = Map.empty,
          items = List.empty
        )

      val expectedResponse: PropertyAssessmentContexted =
        PropertyAssessmentContexted(
          properties = List(
            Property(
              id = Some(13),
              idx = Some("1.13.1"),
              name = None,
              label = Some("13, NEW GEORGE STREET, PLYMOUTH, PL1 1RL"),
              description = None,
              origination = Some("20260407T111712Z"),
              termination = None,
              category = Some(
                CodeMeaning(
                  code = Some("LTX-DOM-PRP"),
                  meaning = Some("Local taxation domain property")
                )
              ),
              `type` = Some(
                CodeMeaning(
                  code = Some("OCC"),
                  meaning = Some("Constituted by reference to actual occupation")
                )
              ),
              `class` = Some(
                CodeMeaning(
                  code = Some("HDT"),
                  meaning = Some("Statutory NDR hereditament")
                )
              ),
              data = Some(
                PropertyData(
                  foreign_ids = List(
                    ForeignId(
                      system = Some("HMRC-VOA_CDB"),
                      location = Some("hmrc/voa/cdb/add_view"),
                      value = Some("27399677000")
                    ),
                    ForeignId(
                      system = Some("National_Address_Gazetteer"),
                      location = Some("https://services.geoplace.co.uk/"),
                      value = Some("27399677001")
                    )
                  ),
                  foreign_names = List.empty,
                  foreign_labels = List.empty,
                  addresses = AddressData(
                    property_full_address = Some("13, NEW GEORGE STREET, PLYMOUTH, PL1 1RL"),
                    address_line_1 = Some("13"),
                    address_postcode = Some("PL1 1RL"),
                    known_as = None
                  ),
                  location = LocationData(
                    local_authority_pseudo_area_code = Some("1160"),
                    ordnance_survey = None,
                    google_maps = None
                  ),
                  assessments = List(expectedAssessment)
                )
              ),
              protodata = Some(List.empty),
              metadata = Some(emptyMetadata),
              compartments = Some(Map.empty),
              items = Some(List.empty)
            )
          )
        )

      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }
  }
}
