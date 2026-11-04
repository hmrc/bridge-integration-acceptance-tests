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

package models

import play.api.libs.json.{JsValue, Json}

object EnquiryRequest {

  val JOB_09 = Json.parse(
    """{
      |  "files": [
      |    {
      |      "node": null,
      |      "mime_type": "application/pdf",
      |      "label": "RYCTB Uploaded Document",
      |      "is_pointer": true,
      |      "pointer": "https://bstiazstdevfileupload.blob.core.windows.net/bridgefiles/SharePoint API Documents V03 1.pdf",
      |      "data": null
      |    }
      |  ],
      |  "product": {
      |    "property": {
      |      "period": {
      |        "effective_from_date": "20050401T000000Z",
      |        "effective_to_date": null
      |      },
      |      "compilation_date": "20050401T000000Z"
      |    },
      |    "person": {
      |      "communications": {
      |        "forenames": "Grace",
      |        "surname": "Mitchell",
      |        "email": "Grace.Mitchell@test.com",
      |        "telephone_number": "07700144456",
      |        "CouncilTaxPayerFirstName": null,
      |        "CouncilTaxPayerLastName": null,
      |        "CouncilTaxPayerEmail": null,
      |        "CouncilTaxPayerNumber": null,
      |        "CouncilTaxPayerContactPreference": "EMAIL",
      |        "CouncilTaxPayerContactAddress": "FLAT 1, 22 ST MARY STREET, CATHAYS, CARDIFF, CF10 1AA"
      |      },
      |      "capacity": {
      |        "code": "TXP",
      |        "meaning": "Council Tax Payer"
      |      }
      |    },
      |    "relationship": {
      |      "manifestation": {
      |        "artifact_code": "CTB",
      |        "protodata_ptr": "https://www.sandwell.gov.uk/council-tax/council-tax-bill-explained"
      |      },
      |      "type": {
      |        "code": "LIB",
      |        "meaning": null
      |      },
      |      "class": {
      |        "code": "CRW",
      |        "meaning": null
      |      }
      |    }
      |  },
      |  "premises": [
      |    {
      |      "unique_statutory_ref": "J09",
      |      "text": "{\"Question\":\"What has changed?\",\"Answer\":\"There have been changes to the local area that have affected my property\"}",
      |      "statements": [
      |        {
      |          "unique_statutory_ref": "S01",
      |          "text": "{\"Question\":\"What permanent changes have taken place in your local area?\",\"Answer\":\"A new residential housing development has been constructed in the local area, increasing the number of homes and changing the character of the neighbourhood.\"}"
      |        }
      |      ],
      |      "information": [
      |        {
      |          "unique_statutory_ref": "",
      |          "text": ""
      |        }
      |      ]
      |    }
      |  ],
      |  "proposition": [
      |    {
      |      "unique_statutory_ref": "P02",
      |      "alterations": [
      |        "{\"Question\":\"When did these permanent changes take place?\",\"Answer\":\"20261001T000000Z\"}"
      |      ]
      |    }
      |  ]
      |}""".stripMargin
  )

  def get(journeyName: EnquiryJobName): JsValue =
    this.getClass
      .getMethod(journeyName.toString)
      .invoke(this)
      .asInstanceOf[JsValue]

}

enum EnquiryJobName:
  case JOB_09
