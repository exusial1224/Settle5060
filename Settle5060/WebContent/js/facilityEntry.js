function orgEntry(orgId){
	var form1 = document.getElementById('orgForm');
	form1.org_pur_id.value = orgId;
	form1.submit();
}