import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OapSql {

	private static final int HOW_MANY_ZONES = 5;
	private static final int BANNERS_PER_CAMPAING = 5;
	private static final String FILE_NAME = "del_oa_l_tests_"+HOW_MANY_ZONES+".sql";
	private int banner = 0;
	private int campaign = 0;
	private int client = 0;
	private int affiliate =0;
	private int zone =0;
	private int adZoneAssoc=0;
	private int campZoneAssoc=0;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		File file = new File(FILE_NAME);
		try {
			FileWriter writer = new FileWriter(file);
			OapSql sql = new OapSql();
			writer.write(sql.getSetup()+"\n\n");
			writer.write(sql.getAffiliate()+"\n\n");
			writer.write(sql.getClient()+"\n\n");
			for (int i = 0; i < HOW_MANY_ZONES; i++) {
				writer.write(sql.getCampaign()+'\n');
				writer.write(sql.getZone()+"\n");
				writer.write(sql.getCampZoneAssoc()+"\n\n");
				for (int j = 0; j < BANNERS_PER_CAMPAING; j++) {
					writer.write(sql.getBanner()+'\n');
					writer.write(sql.getAdZoneAssoc()+'\n');
				}
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getAffiliate() {
		affiliate++;
		return "INSERT INTO `oa_affiliates` " +
				"(`affiliateid`, `name`, `contact`, `email`, " +
				"`website`, `username`, `password`, `permissions`," +
				" `language`, `publiczones`, `agencyid`, `mnemonic`," +
				" `comments`, `last_accepted_agency_agreement`, `updated`, " +
				"`oac_country_code`, `oac_language_id`, `oac_category_id`) " +
				"VALUES (" +
				affiliate + ", 'http://www.test1.org', NULL, '', " +
				"'http://http://www.test1.org', NULL, NULL, NULL, NULL," +
				" 'f', 0, '', NULL, NULL, '2007-11-20 15:09:39', 'AQ'," +
				" 9, 302);";
	}

	private String getZone() {
		zone++;
		return "INSERT INTO `oa_zones` (`zoneid`, `affiliateid`, `zonename`, `description`," +
				" `delivery`, `zonetype`, `what`, `width`, `height`, `chain`, `prepend`, `append`," +
				" `appendtype`, `category`, `ad_selection`, `forceappend`, `inventory_forecast_type`," +
				" `comments`, `cost`, `cost_type`, `cost_variable_id`, `technology_cost`," +
				" `technology_cost_type`, `updated`, `block`, `capping`, `session_capping`) " +
				"VALUES(" +
				zone + ", " +
				affiliate + ", 'http://www.test1.org - Default', '', 0, 3, '', 120, 120, '', ''," +
				" 0x5a6f6e65, 0, '', '', 'f', 0, '', 1.3333, 2, '', NULL, NULL," +
				" '2007-11-20 15:10:34', 0, 0, 0);";
	}

	private String getBanner() {
		banner++;
		return "INSERT INTO `oa_banners` (`bannerid`, `contenttype`, `pluginversion`," +
				" `storagetype`, `filename`, `imageurl`, `htmltemplate`, `htmlcache`," +
				" `width`, `height`, `weight`, `seq`, `target`, `url`, `alt`, `keyword`," +
				" `bannertext`, `description`, `autohtml`, `block`, `capping`," +
				" `compiledlimitation`, `append`, `appendtype`, `bannertype`, `transparent`," +
				" `campaignid`, `adserver`, `session_capping`, `acl_plugins`, `alt_filename`," +
				" `alt_imageurl`, `alt_contenttype`, `comments`, `updated`, `parameters`," +
				" `acls_updated`, `statustext`, `status`) " +
				"VALUES(" +
				banner + ", 'gif', 0, 'sql', '120x120.gif', '', '', '', 120, 120, 1, 0, ''," +
				" 'http://www.wp.pl', '', '', '', '', 'f', 0, 0, 0x74727565, 0x48744d6c202d20," +
				" 0, 0, 0, " +
				campaign+", '', 0, '', '', '', '', '', '2007-11-20 15:14:41'," +
				" 'N;', '2007-11-20 15:14:41', '', 0);";}

	private String getClient() {
		client++;
		return "INSERT INTO `oa_clients` (`clientid`, `clientname`," +
				" `contact`, `email`, `clientusername`, `clientpassword`, `permissions`," +
				" `language`, `report`, `reportinterval`, `reportlastdate`, `reportdeactivate`," +
				" `lb_reporting`, `agencyid`, `comments`, `updated`) " +
				"VALUES(" +
				client + ", 'QA', 'qa', 'nobody@example.com', 'adv', '4acedbc1e89475eeeabf43c3088ed4a0'," +
				" 187, 'english', 't', 7, '2007-11-27', 't', 'f', 0, '', '2007-11-27 12:07:23');";
	}

	private String getCampaign() {
		campaign++;
		return "INSERT INTO `oa_campaigns` (`campaignid`, `campaignname`," +
				" `clientid`, `views`, `clicks`, `conversions`, `expire`," +
				" `activate`, `priority`, `weight`, `target_impression`," +
				" `target_click`, `target_conversion`, `anonymous`, `companion`," +
				" `comments`, `revenue`, `revenue_type`, `updated`, `block`," +
				" `capping`, `session_capping`, `status`) " +
				"VALUES(" +
				campaign + ", 'QA - Default Campaign"+campaign+"', " +
				client + ", 1000, 1000, 1000, '0000-00-00'," +
				" '0000-00-00', -1, 1, 0, 0, 0, 'f', 0, '', 1.0000, 2, '2007-11-20 16:09:08'," +
				" 0, 0, 0, 0);";
	}

	private String getAdZoneAssoc() {
		adZoneAssoc++;
		return "INSERT INTO `oa_ad_zone_assoc` (`ad_zone_assoc_id`, `zone_id`, `ad_id`, " +
				"`priority`, `link_type`, `priority_factor`, `to_be_delivered`) " +
				"VALUES(" +
				adZoneAssoc+", " +
				zone + ", " +
				banner+", 0, 0, 1, 1);";
	}

	private String getCampZoneAssoc() {
		campZoneAssoc++;
		return "INSERT INTO `oa_placement_zone_assoc` (`placement_zone_assoc_id`," +
				" `zone_id`, `placement_id`) " +
				"VALUES(" +
				campZoneAssoc+ ", " +
				zone + ", " +
				campaign + ");";
	}

	private String getSetup() {

		return "INSERT INTO `oa_images` (`filename`, `contents`, `t_stamp`) " +
				"VALUES('120x120.gif', 0x47494638396178007800f700000000000101010202020303030404040505050606060707070808080909090a0a0a0b0b0b0c0c0c0d0d0d0e0e0e0f0f0f1010101111111212121313131414141515151616161717171818181919191a1a1a1b1b1b1c1c1c1d1d1d1e1e1e1f1f1f2020202121212222222323232424242525252626262727272828282929292a2a2a2b2b2b2c2c2c2d2d2d2e2e2e2f2f2f3030303131313232323333333434343535353636363737373838383939393a3a3a3b3b3b3c3c3c3d3d3d3e3e3e3f3f3f4040404141414242424343434444444545454646464747474848484949494a4a4a4b4b4b4c4c4c4d4d4d4e4e4e4f4f4f5050505151515252525353535454545555555656565757575858585959595a5a5a5b5b5b5c5c5c5d5d5d5e5e5e5f5f5f6060606161616262626363636464646565656666666767676868686969696a6a6a6b6b6b6c6c6c6d6d6d6e6e6e6f6f6f7070707171717272727373737474747575757676767777777878787979797a7a7a7b7b7b7c7c7c7d7d7d7e7e7e7f7f7f8080808181818282828383838484848585858686868787878888888989898a8a8a8b8b8b8c8c8c8d8d8d8e8e8e8f8f8f9090909191919292929393939494949595959696969797979898989999999a9a9a9b9b9b9c9c9c9d9d9d9e9e9e9f9f9fa0a0a0a1a1a1a2a2a2a3a3a3a4a4a4a5a5a5a6a6a6a7a7a7a8a8a8a9a9a9aaaaaaabababacacacadadadaeaeaeafafafb0b0b0b1b1b1b2b2b2b3b3b3b4b4b4b5b5b5b6b6b6b7b7b7b8b8b8b9b9b9babababbbbbbbcbcbcbdbdbdbebebebfbfbfc0c0c0c1c1c1c2c2c2c3c3c3c4c4c4c5c5c5c6c6c6c7c7c7c8c8c8c9c9c9cacacacbcbcbcccccccdcdcdcecececfcfcfd0d0d0d1d1d1d2d2d2d3d3d3d4d4d4d5d5d5d6d6d6d7d7d7d8d8d8d9d9d9dadadadbdbdbdcdcdcdddddddedededfdfdfe0e0e0e1e1e1e2e2e2e3e3e3e4e4e4e5e5e5e6e6e6e7e7e7e8e8e8e9e9e9eaeaeaebebebecececedededeeeeeeefefeff0f0f0f1f1f1f2f2f2f3f3f3f4f4f4f5f5f5f6f6f6f7f7f7f8f8f8f9f9f9fafafafbfbfbfcfcfcfdfdfdfefefeffffff2c00000000780078000008ff004d091c48b0a0c18308132a5cc8b0a1c38710234a9c48b1a2c58b18336adcc8b1a3c78f20438a1c49b2a4c9932853aa5cc9b2a5cb973063ca9c49b3a6cd9b3873eadcc9b3a7cf9f40830a1d4ab4a8d1a348932a5dcab4a9d3a750a34a9d4ab5aad5ab58b36addcab5abd7af60c38a1d4bb6acd9b368d3aa5dcbb6addbb770e3ca9ddb145abe7f047185bbdb8e184156dbee8663d5d02ede817af9fa1d085830e19df3fedd25f86f5eb8bdff169b3af76f5bb57fed18469e3cb0f2e5bb9a397b06cd731b2ece047d21666d8ad8bf6a02a1fd53b6d035ec81b205e2a26d1bb729ddbc79fe3e185960b87fb204cafa77ce54bb7fb804dade4670b9c1e6a69e47ff3735bdbaf2c307f38536352f1fc17cf3c8c367254bfd6381de0baa17d8fe7dfcf30829f30f340255461078db9d934f76dda1579080049a62e040e0ed949f74fb15f81f7fe86df3cf6e065d28df7a126ec89e83395dc84a7be3b1e7de40f00d24207621a268ca8af9b4d81f8c26a688228ec139079d74ff8483e175240e941f90048947a491000e244b7b0c0e545c6e990989cb67112ae9e0940b1674e5715946291f75dba4b9cd78aa7d66de761cb6b8197af5a1a9269b9db9e9d372ba7de8e73fc1b1f25c3edb10c64a3ef93ce60b750d0ed4e79f800a24a86485d265e9a59866aae9a69c76eae9a7a0862aeaa8a4966aeaa9a8a6aaeaaaacb6eaeaabb0c6172aebacb4d66aebadb8e6aaebaebcf6eaebafc0060b5440003b," +
				" '2007-11-20 15:06:37');" +
				"INSERT INTO `oa_preference` (`agencyid`, `config_version`, `my_header`," +
				" `my_footer`, `my_logo`, `language`, `name`, `company_name`, `override_gd_imageformat`," +
				" `begin_of_week`, `percentage_decimals`, `type_sql_allow`, `type_url_allow`, `type_web_allow`," +
				" `type_html_allow`, `type_txt_allow`, `banner_html_auto`, `admin`, `admin_pw`, `admin_fullname`," +
				" `admin_email`, `warn_admin`, `warn_agency`, `warn_client`, `warn_limit`, `admin_email_headers`," +
				" `admin_novice`, `default_banner_weight`, `default_campaign_weight`, `default_banner_url`," +
				" `default_banner_destination`, `client_welcome`, `client_welcome_msg`, `publisher_welcome`," +
				" `publisher_welcome_msg`, `content_gzip_compression`, `userlog_email`, `gui_show_campaign_info`," +
				" `gui_show_campaign_preview`, `gui_campaign_anonymous`, `gui_show_banner_info`, `gui_show_banner_preview`," +
				" `gui_show_banner_html`, `gui_show_matching`, `gui_show_parents`, `gui_hide_inactive`," +
				" `gui_link_compact_limit`, `gui_header_background_color`, `gui_header_foreground_color`," +
				" `gui_header_active_tab_color`, `gui_header_text_color`, `gui_invocation_3rdparty_default`," +
				" `qmail_patch`, `updates_enabled`, `updates_cache`, `updates_timestamp`, `updates_last_seen`," +
				" `allow_invocation_plain`, `allow_invocation_plain_nocookies`, `allow_invocation_js`," +
				" `allow_invocation_frame`, `allow_invocation_xmlrpc`, `allow_invocation_local`," +
				" `allow_invocation_interstitial`, `allow_invocation_popup`, `allow_invocation_clickonly`," +
				" `auto_clean_tables`, `auto_clean_tables_interval`, `auto_clean_userlog`," +
				" `auto_clean_userlog_interval`, `auto_clean_tables_vacuum`, `autotarget_factor`," +
				" `maintenance_timestamp`, `compact_stats`, `statslastday`, `statslasthour`," +
				" `default_tracker_status`, `default_tracker_type`, `default_tracker_linkcampaigns`," +
				" `publisher_agreement`, `publisher_agreement_text`, `publisher_payment_modes`, `publisher_currencies`," +
				" `publisher_categories`, `publisher_help_files`, `publisher_default_tax_id`," +
				" `publisher_default_approved`, `more_reports`, `gui_column_id`, `gui_column_requests`," +
				" `gui_column_impressions`, `gui_column_clicks`, `gui_column_ctr`, `gui_column_conversions`," +
				" `gui_column_conversions_pending`, `gui_column_sr_views`, `gui_column_sr_clicks`," +
				" `gui_column_revenue`, `gui_column_cost`, `gui_column_bv`, `gui_column_num_items`," +
				" `gui_column_revcpc`, `gui_column_costcpc`, `gui_column_technology_cost`," +
				" `gui_column_income`, `gui_column_income_margin`, `gui_column_profit`," +
				" `gui_column_margin`, `gui_column_erpm`, `gui_column_erpc`, `gui_column_erps`," +
				" `gui_column_eipm`, `gui_column_eipc`, `gui_column_eips`, `gui_column_ecpm`," +
				" `gui_column_ecpc`, `gui_column_ecps`, `gui_column_epps`, `maintenance_cron_timestamp`," +
				" `warn_limit_days`) " +
				"VALUES(0, 0.000, NULL, NULL, NULL, 'english', NULL, 'mysite.com'," +
				" NULL, 1, 2, 't', 't', 'f', 't', 't', 't', 'oap', 'bfc8d5cb8163a46f9a620b2859a82f40'," +
				" 'Your Name', 'a@a.pl', 't', 't', 't', 100, NULL, 't', 1, 1, NULL, NULL, 't', NULL, 't'," +
				" NULL, 'f', 't', 't', 'f', 'f', 't', 't', 'f', 't', 'f', 'f', 50, NULL, NULL, NULL, NULL," +
				" '0', 'f', 't', 'b:0;', 1196177230, 0.000, 'f', 't', 't', 'f', 'f', 't', 't', 't', 't'," +
				" 'f', 5, 'f', 5, 't', -1, 1196173630, 't', '0000-00-00', 0, 1, 1, 'f', 'f', NULL, NULL," +
				" NULL, NULL, NULL, 'f', 'f', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL," +
				" NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL," +
				" NULL, NULL, NULL, NULL, NULL, NULL, 1195571495, 1);";
	}
}
