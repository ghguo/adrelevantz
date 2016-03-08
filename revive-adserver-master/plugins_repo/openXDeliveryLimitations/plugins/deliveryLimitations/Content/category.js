$(document).ready(function () {
	var selCatLevel1Id = $('select.cat1').find('option:selected').val();
	var selCatLevel2Id = $('select.cat2').find('option:selected').val();
	var selCatLevel3Id = $('select.cat3').find('option:selected').val();
	var level2Opts = new Array;
	$('select.cat2 option').each(function () {
		level2Opts.push($(this));
	});

	$('select.cat2 option').remove().end();
	var level3Opts = new Array;
	$('select.cat3 option').each(function () {
		level3Opts.push($(this));
	});
	$('select.cat3 option').remove().end();

	if (selCatLevel1Id != "0") {
		catLevel2 = $('select.cat2');
		for (var i in level2Opts) {
			var vals = [];
			vals = level2Opts[i][0].value.split("_");
			if (vals[0] == "0" || vals[0] == selCatLevel1Id) {
				catLevel2.append(level2Opts[i]);
			}
		};

		$('select.cat2').find('option').each(function () {
			if ($(this).val() == selCatLevel2Id)
			{ $(this).attr('selected', true); }
		});

		catLevel3 = $('select.cat3');
		val2s = selCatLevel2Id.split("_");
		for (var i in level3Opts) {
			var vals = [];
			vals = level3Opts[i][0].value.split("_");
			if ((vals[0] == "0" && vals[1] == "0") || (vals[0] == val2s[0] && vals[1] == val2s[1])) {
				catLevel3.append(level3Opts[i]);
			}
		};

		$('select.cat3').find('option').each(function () {
			if ($(this).val() == selCatLevel3Id)
			{ $(this).attr('selected', true); }
		});
	}

	$('select.cat1').change(function (event) {
		selCatLevel1Id = $(this).find('option:selected').val();
		catLevel2 = $('select.cat2');
		catLevel2.find('option').remove().end();
		for (var i in level2Opts) {
			var vals = [];
			vals = level2Opts[i][0].value.split("_");
			if (vals[0] == "0" || vals[0] == selCatLevel1Id) {
				catLevel2.append(level2Opts[i]);
			}
		};
		catLevel2.find('option:first').attr('selected', true);

		catLevel3 = $('select.cat3');
		catLevel3.find('option').remove().end();
		for (var i in level3Opts) {
			catLevel3.append(level3Opts[i]);
			break;
		};
		catLevel3.find('option:first').attr('selected', true);
	});

	$('select.cat2').change(function () {
		selCatLevel2Id = $(this).find('option:selected').val().split("_");
		catLevel3 = $('select.cat3');
		catLevel3.find('option').remove().end();
		for (var i in level3Opts) {
			vals = level3Opts[i][0].value.split("_");
			if ((vals[0] == "0" && vals[1] == "0") || (vals[0] == selCatLevel2Id[0] && vals[1] == selCatLevel2Id[1])) {
				catLevel3.append(level3Opts[i]);
			}
		};
		catLevel3.find('option:first').attr('selected', true);
	});

	
	var selPCatLevel1Id = $('select.pcat1').find('option:selected').val();
	var selPCatLevel2Id = $('select.pcat2').find('option:selected').val();
	var level2POpts = new Array;
	$('select.pcat2 option').each(function () {
		level2POpts.push($(this));
	});

	$('select.pcat2 option').remove().end();

	if (selPCatLevel1Id != "0") {
		pcatLevel2 = $('select.pcat2');
		for (var i in level2POpts) {
			var vals = [];
			vals = level2POpts[i][0].value.split("_");
			if (vals[0] == "0" || vals[0] == selPCatLevel1Id) {
				pcatLevel2.append(level2POpts[i]);
			}
		};

		$('select.pcat2').find('option').each(function () {
			if ($(this).val() == selPCatLevel2Id)
			{ $(this).attr('selected', true); }
		});
	}

	$('select.pcat1').change(function (event) {
		selPCatLevel1Id = $(this).find('option:selected').val();
		pcatLevel2 = $('select.pcat2');
		pcatLevel2.find('option').remove().end();
		for (var i in level2POpts) {
			var vals = [];
			vals = level2POpts[i][0].value.split("_");
			if (vals[0] == "0" || vals[0] == selPCatLevel1Id) {
				pcatLevel2.append(level2POpts[i]);
			}
		};
		pcatLevel2.find('option:first').attr('selected', true);
	});
});
