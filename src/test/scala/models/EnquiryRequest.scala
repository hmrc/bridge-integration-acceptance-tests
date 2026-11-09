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
      |"submissionId" : "12345",
      |"submission_date" : "20261012T000000Z",
      |  "files": [
      |    {
      |      "node": "test",
      |      "mime_type": "application/pdf",
      |      "label": "RYCTB Uploaded Document",
      |      "is_pointer": true,
      |      "pointer": "https://bstiazstdevfileupload.blob.core.windows.net/bridgefiles/SharePoint API Documents V03 1.pdf",
      |      "data": "test"
      |    }
      |  ],
      |  "product": {
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

  val JOB_08 = Json.parse(
    """{
      |"submissionId" : "123456",
      |"submission_date" : "20261012T000000Z",
      |  "files": [
      |    {
      |      "node": null,
      |      "mime_type": "application/pdf",
      |      "label": "RYCTB Uploaded Document",
      |      "is_pointer": true,
      |      "pointer": "https://bstiazstdevfileupload.blob.core.windows.net/bridgefiles/SharePoint API Documents V03 1.pdf",
      |      "data": null
      |    },
      |    {
      |      "node": null,
      |      "mime_type": "application/pdf",
      |      "label": "RYCTB Uploaded Document",
      |      "is_pointer": true,
      |      "pointer": "https://bstiazstdevfileupload.blob.core.windows.net/bridgefiles/HVCTS.pdf",
      |      "data": null
      |    }
      |  ],
      |  "product": {
      |    "person": {
      |      "communications": {
      |        "forenames": "John",
      |        "surname": "Smith",
      |        "email": "john.smith@test.com",
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
      |      "unique_statutory_ref": "J08",
      |      "text": "{\"Question\": \"How has the use of the property changed?\", \"Answer\": \"The property is now used as a business\"}",
      |      "statements": [
      |        {
      |          "unique_statutory_ref": "S01",
      |          "text": "{\"Question\": \"Which parts of your home are being used for business?\", \"Answer\": \"One bedroom on the first floor is used as a home office for administrative work and remote working activities. The remainder of the property is used for residential purposes.\"}"
      |        }
      |      ],
      |      "information": [
      |        {
      |          "unique_statutory_ref": "I11",
      |          "text": "{\"Question\": \"Has the address of your home changed? \", \"Answer\": \"Yes. The original property was divided into separate residential units and each unit has been allocated its own address.\"}"
      |        }
      |      ]
      |    }
      |  ],
      |  "proposition": [
      |    {
      |      "unique_statutory_ref": "P02",
      |      "alterations": [
      |       "{\"Question\": \"When did all or part of your property become your home?\", \"Answer\": \"20261001T000000Z\"}"
      |      ]
      |    }
      |  ]
      |}""".stripMargin
  )

  val JOB_07 = Json.parse(
    """{
      |"submissionId" : "1234567",
      |"submission_date" : "20261012T000000Z",
      |  "files": [
      |    {
      |      "node": null,
      |      "mime_type": "application/pdf",
      |      "label": "RYCTB Uploaded Document",
      |      "is_pointer": true,
      |      "pointer": "https://bstiazstdevfileupload.blob.core.windows.net/bridgefiles/SharePoint API Documents V03 1.pdf",
      |      "data": null
      |    },
      |    {
      |      "node": null,
      |      "mime_type": "application/pdf",
      |      "label": "RYCTB Uploaded Document",
      |      "is_pointer": true,
      |      "pointer": "https://bstiazstdevfileupload.blob.core.windows.net/bridgefiles/HVCTS.pdf",
      |      "data": null
      |    }
      |  ],
      |  "product": {
      |    "person": {
      |      "communications": {
      |        "forenames": "John",
      |        "surname": "Smith",
      |        "email": "john.smith@test.com",
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
      |      "unique_statutory_ref": "J07",
      |      "text": "{\"Question\": \"How has the use of the property changed?\", \"Answer\": \"Part or all of the property is now used as a home\"}",
      |      "statements": [
      |        {
      |          "unique_statutory_ref": "S01",
      |          "text" : "{\"Question\": \"Which parts of your property are now used as a home?\", \"Answer\": \"The ground floor and first floor of the property are used as living accommodation, including the kitchen, living room, bedrooms, and bathrooms.\"}"
      |        }
      |      ],
      |      "information": [
      |        {
      |          "unique_statutory_ref": "I11",
      |          "text": "{\"Question\": \"Has the address of your home changed? \", \"Answer\": \"Yes. The original property was divided into separate residential units and new addresses were assigned to each dwelling..\"}"      
      |        }
      |      ]
      |    }
      |  ],
      |  "proposition": [
      |    {
      |      "unique_statutory_ref": "P02",
      |      "alterations": [
      |       "{\"Question\": \"When did all or part of your property become your home?\", \"Answer\": \"20261001T000000Z\"}"
      |      ]
      |    }
      |  ]
      |}""".stripMargin
  )

  val JOB_06 = Json.parse(
    """{
      |"submissionId" : "1234567",
      |"submission_date" : "20261012T000000Z",
      |  "files": [
      |    {
      |      "node": null,
      |      "mime_type": "application/pdf",
      |      "label": "RYCTB Uploaded Document",
      |      "is_pointer": true,
      |      "pointer": "https://bstiazstdevfileupload.blob.core.windows.net/bridgefiles/SharePoint API Documents V03 1.pdf",
      |      "data": null
      |    },
      |    {
      |      "node": null,
      |      "mime_type": "application/pdf",
      |      "label": "RYCTB Uploaded Document",
      |      "is_pointer": true,
      |      "pointer": "https://bstiazstdevfileupload.blob.core.windows.net/bridgefiles/HVCTS.pdf",
      |      "data": null
      |    }
      |  ],
      |  "product": {
      |    "person": {
      |      "communications": {
      |        "forenames": "John",
      |        "surname": "Smith",
      |        "email": "john.smith@test.com",
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
      |      "unique_statutory_ref": "J06",
      |      "text": "{\"Question\": \"How has the property been split or merged?\", \"Answer\": \"The property has been split into two or more separate properties to make smaller properties\"}",
      |      "statements": [
      |        {
      |          "unique_statutory_ref": "S01",
      |          "text": "{\"Question\": \"How has your home been split?\", \"Answer\": \"The property was converted from a single-family house into two self-contained flats. Separate kitchens, bathrooms, and living areas were created, with each flat having its own independent access.\"}"
      |        }
      |      ],
      |      "information": [
      |        {
      |          "unique_statutory_ref": "I11",
      |          "text": "{\"Question\": \"Has the address of your home changed? \", \"Answer\": \"Yes. The property address was updated following the completion of building works and has been assigned a new official address by the local authority.\"}"
      |        }
      |      ]
      |    }
      |  ],
      |  "proposition": [
      |    {
      |      "unique_statutory_ref": "P02",
      |      "alterations": [
      |       "{\"Question\": \"When was the work to split your home completed?\", \"Answer\": \"20261001T000000Z\"}"
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
  case JOB_08
  case JOB_07
  case JOB_06
