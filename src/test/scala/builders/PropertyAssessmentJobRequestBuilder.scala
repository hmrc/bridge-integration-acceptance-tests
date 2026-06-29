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

package builders

import client.WsClient
import play.api.libs.json.Json
import play.api.libs.ws.StandaloneWSResponse
import utils.BaseRequests

object PropertyAssessmentJobRequestBuilder extends BaseRequests {

  def propertyAssessmentData(credId: String): StandaloneWSResponse = {
    val bearerToken  = createBearerToken(credId = credId)
    val assessmentId = "27399677000"

    val baseUri = s"$bridgeIntegrationUrl/property-assessment/$credId/assessment/$assessmentId"

    println(s"service level bearer token *********************************$bearerToken")

    val headers = Map(
      "Authorization" -> s"Bearer $bearerToken",
      "Content-Type"  -> "application/json",
      "Accept"        -> "application/vnd.hmrc.1.0+json"
    )

    val jsonBody = Json.parse("""
      |{"properties":[{"id":13,"idx":"1.13.1","name":null,"label":"13, NEW GEORGE STREET, PLYMOUTH, PL1 1RL","description":null,"origination":"20260407T111712Z","termination":null,"category":{"code":"1.13.1","meaning":"Local taxation domain property"},"type":{"code":"OCC","meaning":"Constituted by reference to actual occupation"},"class":{"code":"HDT","meaning":"Statutory NDR hereditament"},"data":{"foreign_ids":[{"system":"HMRC-VOA_CDB","location":"hmrc/voa/cdb/add_view","value":"27399677000"},{"system":"National_Address_Gazetteer","location":"https://services.geoplace.co.uk/","value":"27399677001"}],"foreign_names":[],"foreign_labels":[],"addresses":{"property_full_address":"13, NEW GEORGE STREET, PLYMOUTH, PL1 1RL","address_line_1":"13","address_postcode":"PL1 1RL","known_as":null},"location":{"local_authority_pseudo_area_code":"1160","ordnance_survey":null,"google_maps":null,"local_authority":"Cardiff"},"assessments":[{"id":13,"idx":"1.13.1","name":null,"label":"Non Domestic Rating Assessment","description":null,"origination":"20260407T111712Z","termination":null,"category":{"code":"LTX-DOM-AST","meaning":"Local taxation domain assessment"},"type":{"code":"CHG","meaning":"To be determined"},"class":{"code":"RLE","meaning":"Register list entry"},"data":{"foreign_ids":[{"system":"HMRC-VOA_CDB","location":"hmrc/voa/cdb/ndr_assessments","value":"27399677000"}],"foreign_names":[],"foreign_labels":[],"property":{"property_id":13,"cdb_property_id":13,"council_tax_reference_number":"123456","council_tax_band":"D"},"use":{"is_composite":"N","is_part_exempt":"N","use_description":"RESTAURANT AND PREMISES RESTAURANT AND PREMISES","improvement_indicator":"IMP10","mixed_use_property":"MIXED"},"valuation_surveys":[{"id":13,"idx":"1.13.1","name":null,"label":"Valuation survey supporting a local taxation rating list entry (assessment)","description":"Valuation Survey","origination":"20260407T111712Z","termination":null,"category":{"code":"LTX-DOM-VAS","meaning":"Local taxation domain valuation survey"},"type":{"code":"SPL","meaning":"Composed of a hierarchy of spatial containers"},"class":{"code":"NIA","meaning":"Areas measured to faces of internal perimeter or party walls"},"data":{"foreign_ids":[{"system":"CDB_VSA_SURVEY","location":"hmrc-voa/cdb/vsa/hereditament_vals","value":"24104677000"}],"foreign_names":[],"foreign_labels":[],"survey":{"id":1,"idx":"1","name":null,"label":"Site","description":null,"origination":null,"termination":null,"category":{"code":"LTX-DOM-CON","meaning":"Temporal spatial unit formed by reference to statutory and common law principles of rateable occupation."},"type":{"code":"HOR","meaning":"A two dimensional horizontal unit of space"},"class":{"code":"SIT","meaning":"Site"},"data":{"foreign_ids":[{"system":"HMRC-VOA_CDB","location":"hmrc/voa/cdb/hereditament_vals","value":"24104688000"}],"foreign_names":[],"foreign_labels":[],"uses":[],"constructions":[],"facilities":[],"artifacts":[{"activity":{"source":null,"value":null},"code":{"source":null,"value":null},"description":{"source":"tom:sql/cdb:ndr/plant_machinery/os_refno:24104677000/description","value":"Plant and Machinery (Goods Lift)"},"quantity":{"source":"tom:sql/cdb:ndr/plant_machinery/os_refno:24104677000/value","value":2550},"units":{"source":null,"value":"GDP"}}],"uninheritances":[],"attributions":[]},"protodata":[],"metadata":{"sending":{"extracting":{"selecting":{}},"transforming":{"filtering":{},"supplementing":{},"recontextualising":{}},"loading":{"readying":{},"assuring":{},"signing":{},"encrypting":{},"sending":{}}},"receiving":{"unloading":{"receiving":{},"decrypting":{},"verifying":{},"assuring":{},"readying":{}},"transforming":{"recontextualising":{},"dropping":{},"restoring":{}},"storing":{"inserting":{}}}},"compartments":{},"items":[]}},"protodata":[],"metadata":{"sending":{"extracting":{"selecting":{}},"transforming":{"filtering":{},"supplementing":{},"recontextualising":{}},"loading":{"readying":{},"assuring":{},"signing":{},"encrypting":{},"sending":{}}},"receiving":{"unloading":{"receiving":{},"decrypting":{},"verifying":{},"assuring":{},"readying":{}},"transforming":{"recontextualising":{},"dropping":{},"restoring":{}},"storing":{"inserting":{}}}},"compartments":{},"items":[]}],"valuations":[],"valuation":{"valuation_method_code":"234","valuation_rateable":76500,"valuation_effective_date":"20260407T111712Z"},"list":{"list_category":"LTX-DOM-LST","list_function":"Charging","list_year":"2023","list_authority_code":"1160","court_code":"45645"},"workflow":{"cdb_job_id":39115380283}},"protodata":[],"metadata":{"sending":{"extracting":{"selecting":{}},"transforming":{"filtering":{},"supplementing":{},"recontextualising":{}},"loading":{"readying":{},"assuring":{},"signing":{},"encrypting":{},"sending":{}}},"receiving":{"unloading":{"receiving":{},"decrypting":{},"verifying":{},"assuring":{},"readying":{}},"transforming":{"recontextualising":{},"dropping":{},"restoring":{}},"storing":{"inserting":{}}}},"compartments":{},"items":[]}]},"protodata":[],"metadata":{"sending":{"extracting":{"selecting":{}},"transforming":{"filtering":{},"supplementing":{},"recontextualising":{}},"loading":{"readying":{},"assuring":{},"signing":{},"encrypting":{},"sending":{}}},"receiving":{"unloading":{"receiving":{},"decrypting":{},"verifying":{},"assuring":{},"readying":{}},"transforming":{"recontextualising":{},"dropping":{},"restoring":{}},"storing":{"inserting":{}}}},"compartments":{},"items":[]}]}
      |""".stripMargin)

    WsClient.post(
      uri = baseUri,
      headers = headers,
      json = jsonBody
    )
  }
}
