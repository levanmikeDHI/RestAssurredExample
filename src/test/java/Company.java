@Test
    public void company101POSTCodeTest() throws JsonProcessingException {
        CompanyForOwner companyForOwner = CompanyHelper.populateCompanyForOwner();
        companyForOwner.setCode("");
        String json = JsonHelper.objectToJsonString(companyForOwner);
        Response response =
                given()
                        .header("authorization", "Bearer " + oauthCustomerToken)
                        .contentType("application/json")
                        .body(json)
                        .post(Helpers.apiCompanyBase)
                        .then()
                        .time(lessThan(Helpers.tenSecondTimeout), TimeUnit.MILLISECONDS)
                        .statusCode(400)
                        .extract().response();


        System.out.print("company101POSTCodeTestResponse " + response.prettyPrint());

        validateResponse(response, "400078","The following column has an invalid length: pst grp name", "pstGrpName");
        validateResponse(response, "400051","The following field(s) are required: pst grp name", "pstGrpName");
    }
