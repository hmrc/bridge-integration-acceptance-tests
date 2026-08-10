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
          List(
            Property(
              Some(672),
              Some("1.672.1"),
              None,
              Some("1 Y Deri Duon, Lisvane, Cardiff,CF14 0AA"),
              None,
              Some("20260804T135936Z"),
              None,
              Some(CodeMeaning(Some("LTX-DOM-PRP"), Some("Local taxation domain property"))),
              Some(CodeMeaning(Some("OCC"), Some("Constituted by reference to actual occupation"))),
              Some(
                CodeMeaning(
                  Some("CVW"),
                  Some("Council tax valuation list for a billing authority in Wales (LGFA92s22B2(b)to3A)")
                )
              ),
              Some(
                PropertyData(
                  List(
                    ForeignId(Some("HMRC-VOA_CDB"), Some("hmrc/voa/cdb/add_view"), Some("27399677000")),
                    ForeignId(
                      Some("National_Address_Gazetteer"),
                      Some("https://services.geoplace.co.uk/"),
                      Some("27399677001")
                    )
                  ),
                  List(ForeignId(Some("CDB"), Some("UK"), Some("27399677001"))),
                  List(ForeignId(Some("CDB"), Some("UK"), Some("27399677001"))),
                  AddressData(Some("1 Y Deri Duon, Lisvane, Cardiff,CF14 0AA"), Some("1"), Some("CF14 0AA"), None),
                  LocationData(Some("1160"), None, None),
                  List(
                    PropertyAssessment(
                      59,
                      "1.59.1",
                      None,
                      "Non Domestic Rating Assessment",
                      None,
                      "20260804T135936Z",
                      None,
                      CodeMeaning(Some("LTX-DOM-AST"), Some("Local taxation domain assessment")),
                      CodeMeaning(Some("CHG"), Some("To be determined")),
                      CodeMeaning(Some("RLE"), Some("Register list entry")),
                      PropertyAssessmentData(
                        List(
                          ForeignId(Some("HMRC-VOA_CDB"), Some("hmrc/voa/cdb/ndr_assessments"), Some("27399677000"))
                        ),
                        List(
                          ForeignId(Some("HMRC-VOA_CDB"), Some("hmrc/voa/cdb/ndr_assessments1"), Some("27399677001"))
                        ),
                        List(
                          ForeignId(Some("HMRC-VOA_CDB"), Some("hmrc/voa/cdb/ndr_assessments1"), Some("27399677001"))
                        ),
                        PropertyReference(672, 672),
                        PropertyUse(Some("N"), Some("N"), Some("RESTAURANT AND PREMISES RESTAURANT AND PREMISES")),
                        List(
                          ValuationSurvey(
                            59,
                            "1.59.1",
                            None,
                            "Valuation survey supporting a local taxation rating list entry (assessment)",
                            Some("Valuation Survey"),
                            Some("20260804T135936Z"),
                            None,
                            CodeMeaning(Some("LTX-DOM-VAS"), Some("Local taxation domain valuation survey")),
                            CodeMeaning(Some("SPL"), Some("Composed of a hierarchy of spatial containers")),
                            CodeMeaning(
                              Some("NIA"),
                              Some("Areas measured to faces of internal perimeter or party walls")
                            ),
                            ValuationSurveyData(
                              List(
                                ForeignId(
                                  Some("CDB_VSA_SURVEY"),
                                  Some("hmrc-voa/cdb/vsa/hereditament_vals"),
                                  Some("24104677000")
                                )
                              ),
                              List(ForeignId(Some("SURV_SYS"), Some("survey.type"), Some("Retail"))),
                              List(ForeignId(Some("SURV_SYS"), Some("surveyor.name"), Some("Surveyor Name"))),
                              SurveyLevelItem(
                                1,
                                "1",
                                None,
                                "Site",
                                None,
                                None,
                                None,
                                CodeMeaning(
                                  Some("LTX-DOM-CON"),
                                  Some(
                                    "Temporal spatial unit formed by reference to statutory and common law principles of rateable occupation."
                                  )
                                ),
                                CodeMeaning(Some("HOR"), Some("A two dimensional horizontal unit of space")),
                                CodeMeaning(Some("SIT"), Some("Site")),
                                SurveyData(
                                  List(
                                    ArtifactRecord(
                                      SourceValue(None, None),
                                      SourceValue(None, None),
                                      SourceValue(
                                        Some("tom:sql/cdb:ndr/plant_machinery/os_refno:24104677000/description"),
                                        Some("Plant and Machinery (Goods Lift)")
                                      ),
                                      QuantitySourceValue(
                                        Some("tom:sql/cdb:ndr/plant_machinery/os_refno:24104677000/value"),
                                        2550
                                      ),
                                      SourceValue(None, Some("GDP"))
                                    )
                                  ),
                                  List(),
                                  List(),
                                  List(),
                                  List(
                                    ForeignId(
                                      Some("HMRC-VOA_CDB"),
                                      Some("hmrc/voa/cdb/hereditament_vals"),
                                      Some("24104677000")
                                    )
                                  ),
                                  List(),
                                  List(),
                                  List(),
                                  List()
                                ),
                                List(),
                                Metadata(
                                  SendingMetadata(
                                    MetadataStage(
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map()
                                    ),
                                    MetadataStage(
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map()
                                    ),
                                    MetadataStage(
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map()
                                    )
                                  ),
                                  ReceivingMetadata(
                                    MetadataStage(
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map()
                                    ),
                                    MetadataStage(
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map()
                                    ),
                                    MetadataStage(
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map(),
                                      Map()
                                    )
                                  )
                                ),
                                Map(),
                                List()
                              )
                            ),
                            List(ProtoData("application/json", "Survey Blob", false, "", """{"doc":"seed","i":1}""")),
                            Metadata(
                              SendingMetadata(
                                MetadataStage(
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map()
                                ),
                                MetadataStage(
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map()
                                ),
                                MetadataStage(
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map()
                                )
                              ),
                              ReceivingMetadata(
                                MetadataStage(
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map()
                                ),
                                MetadataStage(
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map()
                                ),
                                MetadataStage(
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map(),
                                  Map()
                                )
                              )
                            ),
                            Map(),
                            List()
                          )
                        ),
                        List(),
                        ValuationData(Some("234"), Some(76500), Some("20260804T135936Z")),
                        ListData(Some("LTX-DOM-LST"), Some("Charging"), Some("2023"), Some("1160")),
                        WorkflowData(Some(39115380283L))
                      ),
                      List(ProtoData("application/json", "Calc Inputs", false, "", """{"basis":"rcv","i":1}""")),
                      Metadata(
                        SendingMetadata(
                          MetadataStage(
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map()
                          ),
                          MetadataStage(
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map()
                          ),
                          MetadataStage(
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map()
                          )
                        ),
                        ReceivingMetadata(
                          MetadataStage(
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map()
                          ),
                          MetadataStage(
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map()
                          ),
                          MetadataStage(
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map(),
                            Map()
                          )
                        )
                      ),
                      Map(),
                      List()
                    )
                  )
                )
              ),
              Some(List(ProtoData("text/plain", "Notes", false, "", "Property note 1"))),
              Some(
                Metadata(
                  SendingMetadata(
                    MetadataStage(
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map()
                    ),
                    MetadataStage(
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map()
                    ),
                    MetadataStage(
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map()
                    )
                  ),
                  ReceivingMetadata(
                    MetadataStage(
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map()
                    ),
                    MetadataStage(
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map()
                    ),
                    MetadataStage(
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map(),
                      Map()
                    )
                  )
                )
              ),
              Some(Map()),
              Some(List())
            )
          )
        )
      theResponseShouldContainTheFollowingDetails(context, expectedResponse)
    }
  }
}
