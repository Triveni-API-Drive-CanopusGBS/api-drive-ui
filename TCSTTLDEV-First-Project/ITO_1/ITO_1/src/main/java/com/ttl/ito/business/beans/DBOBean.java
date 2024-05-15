package com.ttl.ito.business.beans;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DBOBean {
	private String region_key;
	private int roles_id;
	public int getRoles_id() {
		return roles_id;
	}
	public void setRoles_id(int roles_id) {
		this.roles_id = roles_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getRole_cd() {
		return role_cd;
	}
	public void setRole_cd(String role_cd) {
		this.role_cd = role_cd;
	}
	public String getGroup_cd() {
		return group_cd;
	}
	public void setGroup_cd(String group_cd) {
		this.group_cd = group_cd;
	}
	private String role_name;
	private String role_cd;
	private String group_cd;
	private int unit_id;
	public int getUnit_id() {
		return unit_id;
	}
	public void setUnit_id(int unit_id) {
		this.unit_id = unit_id;
	}
	public int getVehicle_id() {
		return vehicle_id;
	}
	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}
	public float getMin_dist() {
		return min_dist;
	}
	public void setMin_dist(float min_dist) {
		this.min_dist = min_dist;
	}
	public float getMax_dist() {
		return max_dist;
	}
	public void setMax_dist(float max_dist) {
		this.max_dist = max_dist;
	}
	public float getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(float unit_price) {
		this.unit_price = unit_price;
	}
	private int trns_id;
	public int getTrns_id() {
		return trns_id;
	}
	public void setTrns_id(int trns_id) {
		this.trns_id = trns_id;
	}
	public String getTrns_typ() {
		return trns_typ;
	}
	public void setTrns_typ(String trns_typ) {
		this.trns_typ = trns_typ;
	}
	public int getFob_id() {
		return fob_id;
	}
	public void setFob_id(int fob_id) {
		this.fob_id = fob_id;
	}
	public int getComp_id() {
		return comp_id;
	}
	public void setComp_id(int comp_id) {
		this.comp_id = comp_id;
	}
	public int getNo_of_vehicle() {
		return no_of_vehicle;
	}
	public void setNo_of_vehicle(int no_of_vehicle) {
		this.no_of_vehicle = no_of_vehicle;
	}
	private String trns_typ;
	private int fob_id;
	private int comp_id;
	private int no_of_vehicle;
	private int port_id;
	private int variant_id;
	public int getVariant_id() {
		return variant_id;
	}
	public void setVariant_id(int variant_id) {
		this.variant_id = variant_id;
	}
	public String getVariant_cd() {
		return variant_cd;
	}
	public void setVariant_cd(String variant_cd) {
		this.variant_cd = variant_cd;
	}
	public String getC_num() {
		return c_num;
	}
	public void setC_num(String c_num) {
		this.c_num = c_num;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getC_proj_dt() {
		return c_proj_dt;
	}
	public void setC_proj_dt(String c_proj_dt) {
		this.c_proj_dt = c_proj_dt;
	}
	private String variant_cd;
	private String c_num;
	private int cust_id;
	private String c_proj_dt;
	private int emp_id;
	public int getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}
	public String getPh_num() {
		return ph_num;
	}
	public void setPh_num(String ph_num) {
		this.ph_num = ph_num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public Date getLast_login_dt() {
		return last_login_dt;
	}
	public void setLast_login_dt(Date last_login_dt) {
		this.last_login_dt = last_login_dt;
	}
	private String item_cd;
	public String getItem_cd() {
		return item_cd;
	}
	public void setItem_cd(String item_cd) {
		this.item_cd = item_cd;
	}
	public String getItem_nm() {
		return item_nm;
	}
	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}
	public int getNo_of_months() {
		return no_of_months;
	}
	public void setNo_of_months(int no_of_months) {
		this.no_of_months = no_of_months;
	}
	public int getPercent_flg() {
		return percent_flg;
	}
	public void setPercent_flg(int percent_flg) {
		this.percent_flg = percent_flg;
	}
	private String item_nm;
	private int no_of_months;
	private int percent_flg;
	
	private String ph_num;
	private String email;
	private String password;
	private String designation;
	private Date last_login_dt;
	
	
	
	public int getPort_id() {
		return port_id;
	}
	public void setPort_id(int port_id) {
		this.port_id = port_id;
	}
	private int vehicle_id;
	private float min_dist;
	private float max_dist;
	private float unit_price;
	
	public String getRegion_key() {
		return region_key;
	}
	public void setRegion_key(String region_key) {
		this.region_key = region_key;
	}
	private String ACTION_TYPE;
	
	
	public String getACTION_TYPE() {
		return ACTION_TYPE;
	}
	public void setACTION_TYPE(String ACTION_TYPE) {
		ACTION_TYPE = ACTION_TYPE;
	}
	private String action_row;
	public String getAction_row() {
		return action_row;
	}
	public void setAction_row(String action_row) {
		this.action_row = action_row;
	}
	public int getCur_id() {
		return cur_id;
	}
	public void setCur_id(int cur_id) {
		this.cur_id = cur_id;
	}
	public String getCur_nm() {
		return cur_nm;
	}
	public void setCur_nm(String cur_nm) {
		this.cur_nm = cur_nm;
	}
	public String getCur_cd() {
		return cur_cd;
	}
	public void setCur_cd(String cur_cd) {
		this.cur_cd = cur_cd;
	}
	public String getComr_text_a() {
		return comr_text_a;
	}
	public void setComr_text_a(String comr_text_a) {
		this.comr_text_a = comr_text_a;
	}
	public String getComr_text_d() {
		return comr_text_d;
	}
	public void setComr_text_d(String comr_text_d) {
		this.comr_text_d = comr_text_d;
	}
	public String getComr_text_e() {
		return comr_text_e;
	}
	public void setComr_text_e(String comr_text_e) {
		this.comr_text_e = comr_text_e;
	}
	public String getConversion_rate() {
		return conversion_rate;
	}
	public void setConversion_rate(String conversion_rate) {
		this.conversion_rate = conversion_rate;
	}
	public Date getCreat_dt() {
		return creat_dt;
	}
	public void setCreat_dt(Date creat_dt) {
		this.creat_dt = creat_dt;
	}
	public Date getMod_dt() {
		return mod_dt;
	}
	public void setMod_dt(Date mod_dt) {
		this.mod_dt = mod_dt;
	}
	
	public int getIs_active() {
		return is_active;
	}
	public void setIs_active(int is_active) {
		this.is_active = is_active;
	}
	private float prev_percent;
	public float getPrev_percent() {
		return prev_percent;
	}
	public void setPrev_percent(float prev_percent) {
		this.prev_percent = prev_percent;
	}
	private int cur_id;
	private String cur_nm;
	private String cur_cd;
	private String comr_text_a;
	private String comr_text_d;
	private String comr_text_e;
	private String conversion_rate;
	private Date creat_dt;
	private Date mod_dt;
	private String mod_by;
	public String getMod_by() {
		return mod_by;
	}
	public void setMod_by(String mod_by) {
		this.mod_by = mod_by;
	}
	public String getCreat_by() {
		return creat_by;
	}
	public void setCreat_by(String creat_by) {
		this.creat_by = creat_by;
	}
	private String creat_by;
	private int is_active;
	private int price_id;
	public int getPrice_id() {
		return price_id;
	}
	public void setPrice_id(int price_id) {
		this.price_id = price_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getType_of_panel() {
		return type_of_panel;
	}
	public void setType_of_panel(String type_of_panel) {
		this.type_of_panel = type_of_panel;
	}
	public String getCust_type() {
		return cust_type;
	}
	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}
	public int getCol_id() {
		return col_id;
	}
	public void setCol_id(int col_id) {
		this.col_id = col_id;
	}
	public String getCol_val_cd() {
		return col_val_cd;
	}
	public void setCol_val_cd(String col_val_cd) {
		this.col_val_cd = col_val_cd;
	}
	public String getSub_col_val_cd() {
		return sub_col_val_cd;
	}
	public void setSub_col_val_cd(String sub_col_val_cd) {
		this.sub_col_val_cd = sub_col_val_cd;
	}
	public String getAddon_cost_col() {
		return addon_cost_col;
	}
	public void setAddon_cost_col(String addon_cost_col) {
		this.addon_cost_col = addon_cost_col;
	}
	public float getMin_val() {
		return min_val;
	}
	public void setMin_val(float min_val) {
		this.min_val = min_val;
	}
	public float getMax_val() {
		return max_val;
	}
	public void setMax_val(float max_val) {
		this.max_val = max_val;
	}
	public float getAddon_dir_cost() {
		return addon_dir_cost;
	}
	public void setAddon_dir_cost(float addon_dir_cost) {
		this.addon_dir_cost = addon_dir_cost;
	}
	public float getAddon_cost_per() {
		return addon_cost_per;
	}
	public void setAddon_cost_per(float addon_cost_per) {
		this.addon_cost_per = addon_cost_per;
	}
	public int getApprox_cost_flg() {
		return approx_cost_flg;
	}
	public void setApprox_cost_flg(int approx_cost_flg) {
		this.approx_cost_flg = approx_cost_flg;
	}
	public String getError_msg() {
		return error_msg;
	}
	public void setError_msg(String error_msg) {
		this.error_msg = error_msg;
	}
	private int item_id;
	private String type_of_panel;
	private String cust_type;
	private int col_id;
	private String col_val_cd;
	private String sub_col_val_cd;
	private String addon_cost_col;
	private float min_val;
	private float max_val;
	private float addon_dir_cost;
	private float addon_cost_per;
	private int approx_cost_flg;
	private String error_msg;
	private int sub_item_id;
	public int getSub_item_id() {
		return sub_item_id;
	}
	public void setSub_item_id(int sub_item_id) {
		this.sub_item_id = sub_item_id;
	}
	public String getPrice_code() {
		return price_code;
	}
	public void setPrice_code(String price_code) {
		this.price_code = price_code;
	}
	public float getPrice_15() {
		return price_15;
	}
	public void setPrice_15(float price_15) {
		this.price_15 = price_15;
	}
	public float getPrice_30() {
		return price_30;
	}
	public void setPrice_30(float price_30) {
		this.price_30 = price_30;
	}
	public String getItem_error_msg() {
		return item_error_msg;
	}
	public void setItem_error_msg(String item_error_msg) {
		this.item_error_msg = item_error_msg;
	}
	private String price_code;
	private float price_15;
	private float price_30;
	private String item_error_msg;
	
	private String ele_type;
	public String getEle_type() {
		return ele_type;
	}
	public void setEle_type(String ele_type) {
		this.ele_type = ele_type;
	}
	public int getItem_order() {
		return item_order;
	}
	public void setItem_order(int item_order) {
		this.item_order = item_order;
	}
	public int getItem_appl_ind() {
		return item_appl_ind;
	}
	public void setItem_appl_ind(int item_appl_ind) {
		this.item_appl_ind = item_appl_ind;
	}
	public int getSub_item_flg() {
		return sub_item_flg;
	}
	public void setSub_item_flg(int sub_item_flg) {
		this.sub_item_flg = sub_item_flg;
	}
	public int getPanel_depend_flg() {
		return panel_depend_flg;
	}
	public void setPanel_depend_flg(int panel_depend_flg) {
		this.panel_depend_flg = panel_depend_flg;
	}
	public int getLink_flg() {
		return link_flg;
	}
	public void setLink_flg(int link_flg) {
		this.link_flg = link_flg;
	}
	public int getDepend_flg() {
		return depend_flg;
	}
	public void setDepend_flg(int depend_flg) {
		this.depend_flg = depend_flg;
	}
	public int getTech_flg() {
		return tech_flg;
	}
	public void setTech_flg(int tech_flg) {
		this.tech_flg = tech_flg;
	}
	public int getComr_flg() {
		return comr_flg;
	}
	public void setComr_flg(int comr_flg) {
		this.comr_flg = comr_flg;
	}
	public String getHeader_text() {
		return header_text;
	}
	public void setHeader_text(String header_text) {
		this.header_text = header_text;
	}
	public String getFooter_text() {
		return footer_text;
	}
	public void setFooter_text(String footer_text) {
		this.footer_text = footer_text;
	}
	public int getIs_enable() {
		return is_enable;
	}
	public void setIs_enable(int is_enable) {
		this.is_enable = is_enable;
	}
	public String getHearer_nm() {
		return hearer_nm;
	}
	public void setHearer_nm(String hearer_nm) {
		this.hearer_nm = hearer_nm;
	}
	public String getExclusion_nm() {
		return exclusion_nm;
	}
	public void setExclusion_nm(String exclusion_nm) {
		this.exclusion_nm = exclusion_nm;
	}
	public int getCust_typ_depend_flg() {
		return cust_typ_depend_flg;
	}
	public void setCust_typ_depend_flg(int cust_typ_depend_flg) {
		this.cust_typ_depend_flg = cust_typ_depend_flg;
	}
	private int item_order;
	private int item_appl_ind;
	private int sub_item_flg;
	private int panel_depend_flg;
	private int link_flg;
	private int depend_flg;
	private int tech_flg;
	private int comr_flg;
	private String header_text;
	private String footer_text;
	private int is_enable;
	private String hearer_nm;
	private String exclusion_nm;
	private int cust_typ_depend_flg;
	
	private int dt_frm_flg;
	private String col_val_nm;
	public String getCol_val_nm() {
		return col_val_nm;
	}
	public void setCol_val_nm(String col_val_nm) {
		this.col_val_nm = col_val_nm;
	}
	public String getCol_val_cd_sym() {
		return col_val_cd_sym;
	}
	public void setCol_val_cd_sym(String col_val_cd_sym) {
		this.col_val_cd_sym = col_val_cd_sym;
	}
	public int getSub_col_val_flg() {
		return sub_col_val_flg;
	}
	public void setSub_col_val_flg(int sub_col_val_flg) {
		this.sub_col_val_flg = sub_col_val_flg;
	}
	public String getSub_col_val_nm() {
		return sub_col_val_nm;
	}
	public void setSub_col_val_nm(String sub_col_val_nm) {
		this.sub_col_val_nm = sub_col_val_nm;
	}
	public int getDefault_flg_15() {
		return default_flg_15;
	}
	public void setDefault_flg_15(int default_flg_15) {
		this.default_flg_15 = default_flg_15;
	}
	public int getDefault_flg_30() {
		return default_flg_30;
	}
	public void setDefault_flg_30(int default_flg_30) {
		this.default_flg_30 = default_flg_30;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getAdd_on_flg() {
		return add_on_flg;
	}
	public void setAdd_on_flg(int add_on_flg) {
		this.add_on_flg = add_on_flg;
	}
	public int getAdd_on_diff_ds() {
		return add_on_diff_ds;
	}
	public void setAdd_on_diff_ds(int add_on_diff_ds) {
		this.add_on_diff_ds = add_on_diff_ds;
	}
	public int getCalc_link_flg() {
		return calc_link_flg;
	}
	public void setCalc_link_flg(int calc_link_flg) {
		this.calc_link_flg = calc_link_flg;
	}
	public int getDisp_ind() {
		return disp_ind;
	}
	public void setDisp_ind(int disp_ind) {
		this.disp_ind = disp_ind;
	}
	public String getDisable_col_va_cd() {
		return disable_col_va_cd;
	}
	public void setDisable_col_va_cd(String disable_col_va_cd) {
		this.disable_col_va_cd = disable_col_va_cd;
	}
	public int getInput_cost_flg() {
		return input_cost_flg;
	}
	public void setInput_cost_flg(int input_cost_flg) {
		this.input_cost_flg = input_cost_flg;
	}
	private String col_val_cd_sym;
	private int sub_col_val_flg;
	private String sub_col_val_nm;
	private int default_flg_15;
	private int default_flg_30;
	private int order_id;
	private int add_on_flg;
	private int add_on_diff_ds;
	private int calc_link_flg;
	private int disp_ind;
	private String disable_col_va_cd;
	private int input_cost_flg;
	private int del_col_flg;
	private int instr_id;
	public int getInstr_id() {
		return instr_id;
	}
	public void setInstr_id(int instr_id) {
		this.instr_id = instr_id;
	}
	public String getType_of_gov() {
		return type_of_gov;
	}
	public void setType_of_gov(String type_of_gov) {
		this.type_of_gov = type_of_gov;
	}
	public String getTurb_cd() {
		return turb_cd;
	}
	public void setTurb_cd(String turb_cd) {
		this.turb_cd = turb_cd;
	}
	public int getCond_type_id() {
		return cond_type_id;
	}
	public void setCond_type_id(int cond_type_id) {
		this.cond_type_id = cond_type_id;
	}
	public String getType_of_instr() {
		return type_of_instr;
	}
	public void setType_of_instr(String type_of_instr) {
		this.type_of_instr = type_of_instr;
	}
	public String getInstr_type_nm() {
		return instr_type_nm;
	}
	public void setInstr_type_nm(String instr_type_nm) {
		this.instr_type_nm = instr_type_nm;
	}
	public String getInstr_desc() {
		return instr_desc;
	}
	public void setInstr_desc(String instr_desc) {
		this.instr_desc = instr_desc;
	}
	public String getInstr_mounting() {
		return instr_mounting;
	}
	public void setInstr_mounting(String instr_mounting) {
		this.instr_mounting = instr_mounting;
	}
	public int getQty_1001_logic() {
		return qty_1001_logic;
	}
	public void setQty_1001_logic(int qty_1001_logic) {
		this.qty_1001_logic = qty_1001_logic;
	}
	public int getQty_1002_logic() {
		return qty_1002_logic;
	}
	public void setQty_1002_logic(int qty_1002_logic) {
		this.qty_1002_logic = qty_1002_logic;
	}
	public int getQty_2003_logic() {
		return qty_2003_logic;
	}
	public void setQty_2003_logic(int qty_2003_logic) {
		this.qty_2003_logic = qty_2003_logic;
	}
	public int getCost_1001() {
		return cost_1001;
	}
	public void setCost_1001(int cost_1001) {
		this.cost_1001 = cost_1001;
	}
	public int getApprox_1002_flg() {
		return approx_1002_flg;
	}
	public void setApprox_1002_flg(int approx_1002_flg) {
		this.approx_1002_flg = approx_1002_flg;
	}
	public int getCost_2003() {
		return cost_2003;
	}
	public void setCost_2003(int cost_2003) {
		this.cost_2003 = cost_2003;
	}
	public int getApprox_2003_flg() {
		return approx_2003_flg;
	}
	public void setApprox_2003_flg(int approx_2003_flg) {
		this.approx_2003_flg = approx_2003_flg;
	}
	public int getCost_ce_1001() {
		return cost_ce_1001;
	}
	public void setCost_ce_1001(int cost_ce_1001) {
		this.cost_ce_1001 = cost_ce_1001;
	}
	public int getApprox_1001_ce_flg() {
		return approx_1001_ce_flg;
	}
	public void setApprox_1001_ce_flg(int approx_1001_ce_flg) {
		this.approx_1001_ce_flg = approx_1001_ce_flg;
	}
	public int getCost_ce_1002() {
		return cost_ce_1002;
	}
	public void setCost_ce_1002(int cost_ce_1002) {
		this.cost_ce_1002 = cost_ce_1002;
	}
	public int getApprox_1002_ce_flg() {
		return approx_1002_ce_flg;
	}
	public void setApprox_1002_ce_flg(int approx_1002_ce_flg) {
		this.approx_1002_ce_flg = approx_1002_ce_flg;
	}
	public int getCost_ce_2003() {
		return cost_ce_2003;
	}
	public void setCost_ce_2003(int cost_ce_2003) {
		this.cost_ce_2003 = cost_ce_2003;
	}
	public int getApprox_2003_ce_flg() {
		return approx_2003_ce_flg;
	}
	public void setApprox_2003_ce_flg(int approx_2003_ce_flg) {
		this.approx_2003_ce_flg = approx_2003_ce_flg;
	}
	private String type_of_gov;
	private String turb_cd;
	private int cond_type_id;
	private String type_of_instr;
	private String instr_type_nm;
	private String instr_desc;
	private String instr_mounting;
	private int qty_1001_logic;
	private int qty_1002_logic;
	private int qty_2003_logic;
	private int cost_1001;
	private int approx_1002_flg;
	private int cost_2003;
	private int approx_2003_flg;
	private int cost_ce_1001;
	private int approx_1001_ce_flg;
	private int cost_ce_1002;
	private int approx_1002_ce_flg;
	private int cost_ce_2003;
	private int approx_2003_ce_flg;
	private int add_instr_id;
	private int vms_id;
	public int getVms_id() {
		return vms_id;
	}
	public void setVms_id(int vms_id) {
		this.vms_id = vms_id;
	}
	public int getFrm_id() {
		return frm_id;
	}
	public void setFrm_id(int frm_id) {
		this.frm_id = frm_id;
	}
	public int getAdd_prb_flg() {
		return add_prb_flg;
	}
	public void setAdd_prb_flg(int add_prb_flg) {
		this.add_prb_flg = add_prb_flg;
	}
	public int getType_deflt_flg() {
		return type_deflt_flg;
	}
	public void setType_deflt_flg(int type_deflt_flg) {
		this.type_deflt_flg = type_deflt_flg;
	}
	public int getMake_deflt_flg() {
		return make_deflt_flg;
	}
	public void setMake_deflt_flg(int make_deflt_flg) {
		this.make_deflt_flg = make_deflt_flg;
	}
	public int getAlt_make_deflt_flg() {
		return alt_make_deflt_flg;
	}
	public void setAlt_make_deflt_flg(int alt_make_deflt_flg) {
		this.alt_make_deflt_flg = alt_make_deflt_flg;
	}
	public String getAlt_make() {
		return alt_make;
	}
	public void setAlt_make(String alt_make) {
		this.alt_make = alt_make;
	}
	public String getGear_box() {
		return gear_box;
	}
	public void setGear_box(String gear_box) {
		this.gear_box = gear_box;
	}
	public int getGear_box_deflt_flg() {
		return gear_box_deflt_flg;
	}
	public void setGear_box_deflt_flg(int gear_box_deflt_flg) {
		this.gear_box_deflt_flg = gear_box_deflt_flg;
	}
	private int frm_id;
	private int add_prb_flg;
	private int type_deflt_flg;
	private int make_deflt_flg;
	private int alt_make_deflt_flg;
	private String alt_make;
	private String gear_box;
	private int gear_box_deflt_flg;
	
	private int frm_pow_id;
	public int getFrm_pow_id() {
		return frm_pow_id;
	}
	public void setFrm_pow_id(int frm_pow_id) {
		this.frm_pow_id = frm_pow_id;
	}
	public String getBgm_type() {
		return bgm_type;
	}
	public void setBgm_type(String bgm_type) {
		this.bgm_type = bgm_type;
	}
	public float getBgm_rating() {
		return bgm_rating;
	}
	public void setBgm_rating(float bgm_rating) {
		this.bgm_rating = bgm_rating;
	}
	public int getDeflt_flg() {
		return deflt_flg;
	}
	public void setDeflt_flg(int deflt_flg) {
		this.deflt_flg = deflt_flg;
	}
	
	private int dept_id;
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getDept_cd() {
		return dept_cd;
	}
	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
	}
	public String getDept_name() {
		return dept_name;
	}
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	
	private int ec_id;
	public int getEc_id() {
		return ec_id;
	}
	public void setEc_id(int ec_id) {
		this.ec_id = ec_id;
	}
	public int getCond_typ_id() {
		return cond_typ_id;
	}
	public void setCond_typ_id(int cond_typ_id) {
		this.cond_typ_id = cond_typ_id;
	}
	public String getTyp_of_charge() {
		return typ_of_charge;
	}
	public void setTyp_of_charge(String typ_of_charge) {
		this.typ_of_charge = typ_of_charge;
	}
	public int getLoading_id() {
		return loading_id;
	}
	public void setLoading_id(int loading_id) {
		this.loading_id = loading_id;
	}
	public int getLoadging_id() {
		return loadging_id;
	}
	public void setLoadging_id(int loadging_id) {
		this.loadging_id = loadging_id;
	}
	public String getAssigned_to() {
		return assigned_to;
	}
	public void setAssigned_to(String assigned_to) {
		this.assigned_to = assigned_to;
	}
	public int getStatus_id() {
		return status_id;
	}
	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}
	private int mtrl_id;
	public int getMtrl_id() {
		return mtrl_id;
	}
	public void setMtrl_id(int mtrl_id) {
		this.mtrl_id = mtrl_id;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public String getType_of_struct() {
		return type_of_struct;
	}
	public void setType_of_struct(String type_of_struct) {
		this.type_of_struct = type_of_struct;
	}
	private int cat_id;
	private String type_of_struct;
	
	private int region_id;
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	private int pkg_id;
	public int getPkg_id() {
		return pkg_id;
	}
	public void setPkg_id(int pkg_id) {
		this.pkg_id = pkg_id;
	}
	public String getCust_typ() {
		return cust_typ;
	}
	public void setCust_typ(String cust_typ) {
		this.cust_typ = cust_typ;
	}
	public String getPkg_typ() {
		return pkg_typ;
	}
	public void setPkg_typ(String pkg_typ) {
		this.pkg_typ = pkg_typ;
	}
	private String cust_typ;
	private String pkg_typ;
	private int f2f_mast_id;
	public int getF2f_mast_id() {
		return f2f_mast_id;
	}
	public void setF2f_mast_id(int f2f_mast_id) {
		this.f2f_mast_id = f2f_mast_id;
	}
	public int getBleed_typ_id() {
		return bleed_typ_id;
	}
	public void setBleed_typ_id(int bleed_typ_id) {
		this.bleed_typ_id = bleed_typ_id;
	}
	public Float getPer_mwp_rice() {
		return per_mwp_rice;
	}
	public void setPer_mwp_rice(Float per_mwp_rice) {
		this.per_mwp_rice = per_mwp_rice;
	}
	public int getUn_item_id() {
		return un_item_id;
	}
	public void setUn_item_id(int un_item_id) {
		this.un_item_id = un_item_id;
	}
	public String getScop_cd() {
		return scop_cd;
	}
	public void setScop_cd(String scop_cd) {
		this.scop_cd = scop_cd;
	}
	public String getUn_item_cd() {
		return un_item_cd;
	}
	public void setUn_item_cd(String un_item_cd) {
		this.un_item_cd = un_item_cd;
	}
	public String getUn_item_nm() {
		return un_item_nm;
	}
	public void setUn_item_nm(String un_item_nm) {
		this.un_item_nm = un_item_nm;
	}
	public String getUn_parent_cd() {
		return un_parent_cd;
	}
	public void setUn_parent_cd(String un_parent_cd) {
		this.un_parent_cd = un_parent_cd;
	}
	public int getItem_flg() {
		return item_flg;
	}
	public void setItem_flg(int item_flg) {
		this.item_flg = item_flg;
	}
	public int getSub_item_typ_flg() {
		return sub_item_typ_flg;
	}
	public void setSub_item_typ_flg(int sub_item_typ_flg) {
		this.sub_item_typ_flg = sub_item_typ_flg;
	}
	public int getF2f_add_on() {
		return f2f_add_on;
	}
	public void setF2f_add_on(int f2f_add_on) {
		this.f2f_add_on = f2f_add_on;
	}
	private int bleed_typ_id;
	private Float per_mwp_rice;
	
	
	
	private int un_item_id;
	private String scop_cd;
	private String un_item_cd;
	private String un_item_nm;
	private String un_parent_cd;
	private int item_flg;
	private int sub_item_typ_flg;
	private int f2f_add_on;
	
	
	private int user_id;
	
	private int col_val_id;
	public int getCol_val_id() {
		return col_val_id;
	}
	public void setCol_val_id(int col_val_id) {
		this.col_val_id = col_val_id;
	}
	public int getSub_item_typ_id() {
		return sub_item_typ_id;
	}
	public void setSub_item_typ_id(int sub_item_typ_id) {
		this.sub_item_typ_id = sub_item_typ_id;
	}
	private int sub_item_typ_id;
	private int cond_typ_id;
	private String typ_of_charge;
	private int loading_id;
	private int loadging_id;
	private String assigned_to;
	private int status_id;
	private String dept_cd;
	private String dept_name;
	
	private String bgm_type;
	private float bgm_rating;
	private int deflt_flg;
	private float eop_motor_rating;
	public float getEop_motor_rating() {
		return eop_motor_rating;
	}
	public void setEop_motor_rating(float eop_motor_rating) {
		this.eop_motor_rating = eop_motor_rating;
	}
	public String getPump_type() {
		return pump_type;
	}
	public void setPump_type(String pump_type) {
		this.pump_type = pump_type;
	}
	public int getBg_hr_rate() {
		return bg_hr_rate;
	}
	public void setBg_hr_rate(int bg_hr_rate) {
		this.bg_hr_rate = bg_hr_rate;
	}
	public float getDc_output_vol() {
		return dc_output_vol;
	}
	public void setDc_output_vol(float dc_output_vol) {
		this.dc_output_vol = dc_output_vol;
	}
	private float total_price;
	public float getTotal_price() {
		return total_price;
	}
	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}
	private String pump_type;
	private int bg_hr_rate;
	private float dc_output_vol;
	
	
	
	
	public int getAdd_instr_id() {
		return add_instr_id;
	}
	public void setAdd_instr_id(int add_instr_id) {
		this.add_instr_id = add_instr_id;
	}
	public String getAdd_instr_cd() {
		return add_instr_cd;
	}
	public void setAdd_instr_cd(String add_instr_cd) {
		this.add_instr_cd = add_instr_cd;
	}
	public String getAdd_instr_nm() {
		return add_instr_nm;
	}
	public void setAdd_instr_nm(String add_instr_nm) {
		this.add_instr_nm = add_instr_nm;
	}
	private String add_instr_cd;
	private String add_instr_nm;
	
	private int approx_1001_flg;
	public int getApprox_1001_flg() {
		return approx_1001_flg;
	}
	public void setApprox_1001_flg(int approx_1001_flg) {
		this.approx_1001_flg = approx_1001_flg;
	}
	public int getCost_1002() {
		return cost_1002;
	}
	public void setCost_1002(int cost_1002) {
		this.cost_1002 = cost_1002;
	}
	private int cost_1002;
	
	
	public int getDel_col_flg() {
		return del_col_flg;
	}
	public void setDel_col_flg(int del_col_flg) {
		this.del_col_flg = del_col_flg;
	}
	public int getDt_frm_flg() {
		return dt_frm_flg;
	}
	public void setDt_frm_flg(int dt_frm_flg) {
		this.dt_frm_flg = dt_frm_flg;
	}
	private String action_type;
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	private String actionType;
	private String actiontype;
	public String getActiontype() {
		return actiontype;
	}
	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public String getActionRow() {
		return actionRow;
	}
	public void setActionRow(String actionRow) {
		this.actionRow = actionRow;
	}
	public float eopMotarRating;
	public float getEopMotarRating() {
		return eopMotarRating;
	}
	public void setEopMotarRating(float eopMotarRating) {
		this.eopMotarRating = eopMotarRating;
	}
	public String getPumpType() {
		return pumpType;
	}
	public void setPumpType(String pumpType) {
		this.pumpType = pumpType;
	}
	public int getBgHrRate() {
		return bgHrRate;
	}
	public void setBgHrRate(int bgHrRate) {
		this.bgHrRate = bgHrRate;
	}
	public float getDcOutputVol() {
		return dcOutputVol;
	}
	public void setDcOutputVol(float dcOutputVol) {
		this.dcOutputVol = dcOutputVol;
	}
	private String pumpType;
	public int bgHrRate;
	public float dcOutputVol;
	private String actionRow;
	private int categoryCreatedBy;
	public int getCategoryCreatedBy() {
		return categoryCreatedBy;
	}
	public void setCategoryCreatedBy(int categoryCreatedBy) {
		this.categoryCreatedBy = categoryCreatedBy;
	}
	public int getCategoryModifiedBy() {
		return categoryModifiedBy;
	}
	public void setCategoryModifiedBy(int categoryModifiedBy) {
		this.categoryModifiedBy = categoryModifiedBy;
	}
	private int categoryModifiedBy;
	private Date categoryCreatedDate;
	public Date getCategoryCreatedDate() {
		return categoryCreatedDate;
	}
	public void setCategoryCreatedDate(Date categoryCreatedDate) {
		this.categoryCreatedDate = categoryCreatedDate;
	}
	public Date getCategoryModifiedDate() {
		return categoryModifiedDate;
	}
	public void setCategoryModifiedDate(Date categoryModifiedDate) {
		this.categoryModifiedDate = categoryModifiedDate;
	}
	private Date categoryModifiedDate;
	private int slNum;
	public int getSlNum() {
		return slNum;
	}
	public void setSlNum(int slNum) {
		this.slNum = slNum;
	}
	private String prevAddOnCostCol;
	public String getPrevAddOnCostCol() {
		return prevAddOnCostCol;
	}
	public void setPrevAddOnCostCol(String prevAddOnCostCol) {
		this.prevAddOnCostCol = prevAddOnCostCol;
	}
	private float prevTotalPrice;
	public float getPrevTotalPrice() {
		return prevTotalPrice;
	}
	public void setPrevTotalPrice(float prevTotalPrice) {
		this.prevTotalPrice = prevTotalPrice;
	}
	public int getPrevDefaultFlag() {
		return prevDefaultFlag;
	}
	public void setPrevDefaultFlag(int prevDefaultFlag) {
		this.prevDefaultFlag = prevDefaultFlag;
	}
	
	
	private int prevDefaultFlag;
	private float qty;
	public float getQty() {
		return qty;
	}
	public void setQty(float qty) {
		this.qty = qty;
	}
	private int prevTechFlag;
	public int getPrevTechFlag() {
		return prevTechFlag;
	}
	public void setPrevTechFlag(int prevTechFlag) {
		this.prevTechFlag = prevTechFlag;
	}
	private String power;
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	private int bleedTypeId;
	public int getBleedTypeId() {
		return bleedTypeId;
	}
	public void setBleedTypeId(int bleedTypeId) {
		this.bleedTypeId = bleedTypeId;
	}
	public String getBleedTypeName() {
		return bleedTypeName;
	}
	public void setBleedTypeName(String bleedTypeName) {
		this.bleedTypeName = bleedTypeName;
	}
	private String bleedTypeName;
	private int quantity1001Logic;
	public int getQuantity1001Logic() {
		return quantity1001Logic;
	}
	public void setQuantity1001Logic(int quantity1001Logic) {
		this.quantity1001Logic = quantity1001Logic;
	}
	public int getQuantity1002Logic() {
		return quantity1002Logic;
	}
	public void setQuantity1002Logic(int quantity1002Logic) {
		this.quantity1002Logic = quantity1002Logic;
	}
	public int getQuantity2003Logic() {
		return quantity2003Logic;
	}
	public void setQuantity2003Logic(int quantity2003Logic) {
		this.quantity2003Logic = quantity2003Logic;
	}
	public float getCost1001() {
		return cost1001;
	}
	public void setCost1001(float cost1001) {
		this.cost1001 = cost1001;
	}
	public int getApprox1001Flag() {
		return approx1001Flag;
	}
	public void setApprox1001Flag(int approx1001Flag) {
		this.approx1001Flag = approx1001Flag;
	}
	public float getCost1002() {
		return cost1002;
	}
	public void setCost1002(float cost1002) {
		this.cost1002 = cost1002;
	}
	public int getApprox1002Flag() {
		return approx1002Flag;
	}
	public void setApprox1002Flag(int approx1002Flag) {
		this.approx1002Flag = approx1002Flag;
	}
	public float getCost2003() {
		return cost2003;
	}
	public void setCost2003(float cost2003) {
		this.cost2003 = cost2003;
	}
	public int getApprox20003Flag() {
		return approx20003Flag;
	}
	public void setApprox20003Flag(int approx20003Flag) {
		this.approx20003Flag = approx20003Flag;
	}
	public float getCostCe1001() {
		return costCe1001;
	}
	public void setCostCe1001(float costCe1001) {
		this.costCe1001 = costCe1001;
	}
	public int getApprox1001CeFlag() {
		return approx1001CeFlag;
	}
	public void setApprox1001CeFlag(int approx1001CeFlag) {
		this.approx1001CeFlag = approx1001CeFlag;
	}
	public float getCostCe1002() {
		return costCe1002;
	}
	public void setCostCe1002(float costCe1002) {
		this.costCe1002 = costCe1002;
	}
	public int getApprox1002CeFlag() {
		return approx1002CeFlag;
	}
	public void setApprox1002CeFlag(int approx1002CeFlag) {
		this.approx1002CeFlag = approx1002CeFlag;
	}
	public float getCostCe2003() {
		return costCe2003;
	}
	public void setCostCe2003(float costCe2003) {
		this.costCe2003 = costCe2003;
	}
	public int getApprox20003CeFlag() {
		return approx20003CeFlag;
	}
	public void setApprox20003CeFlag(int approx20003CeFlag) {
		this.approx20003CeFlag = approx20003CeFlag;
	}
	private int quantity1002Logic;
	private int quantity2003Logic;
	private float cost1001;
	private int approx1001Flag;
	private float cost1002;
	private int approx1002Flag;
	private int PrevApprox1002Flag;
	private String prevInstrDesc;
	public String getPrevInstrDesc() {
		return prevInstrDesc;
	}
	public void setPrevInstrDesc(String prevInstrDesc) {
		this.prevInstrDesc = prevInstrDesc;
	}
	public String getPrevInstrMounting() {
		return prevInstrMounting;
	}
	public void setPrevInstrMounting(String prevInstrMounting) {
		this.prevInstrMounting = prevInstrMounting;
	}
	public int getPrevQuantity1001Logic() {
		return prevQuantity1001Logic;
	}
	public void setPrevQuantity1001Logic(int prevQuantity1001Logic) {
		this.prevQuantity1001Logic = prevQuantity1001Logic;
	}
	private String prevInstrMounting;
	private int prevQuantity1001Logic;
	public int getPrevApprox1002Flag() {
		return PrevApprox1002Flag;
	}
	public void setPrevApprox1002Flag(int prevApprox1002Flag) {
		PrevApprox1002Flag = prevApprox1002Flag;
	}
	private float prevCost;
	public float getPrevCost() {
		return prevCost;
	}
	public void setPrevCost(float prevCost) {
		this.prevCost = prevCost;
	}
	private float cost2003;
	private int approx20003Flag;
	private float costCe1001;
	private int approx1001CeFlag;
	private float costCe1002;
	private int approx1002CeFlag;
	private float costCe2003;
	private int approx20003CeFlag;
	
	private int PrevQuantity1002Logic;
	public int getPrevQuantity1002Logic() {
		return PrevQuantity1002Logic;
	}
	public void setPrevQuantity1002Logic(int prevQuantity1002Logic) {
		PrevQuantity1002Logic = prevQuantity1002Logic;
	}
	public int getPrevQuantity2003Logic() {
		return PrevQuantity2003Logic;
	}
	public void setPrevQuantity2003Logic(int prevQuantity2003Logic) {
		PrevQuantity2003Logic = prevQuantity2003Logic;
	}
	public float getPrevCost1001() {
		return PrevCost1001;
	}
	public void setPrevCost1001(float prevCost1001) {
		PrevCost1001 = prevCost1001;
	}
	public int getPrevApprox1001Flag() {
		return PrevApprox1001Flag;
	}
	public void setPrevApprox1001Flag(int prevApprox1001Flag) {
		PrevApprox1001Flag = prevApprox1001Flag;
	}
	public float getPrevCost1002() {
		return PrevCost1002;
	}
	public void setPrevCost1002(float prevCost1002) {
		PrevCost1002 = prevCost1002;
	}
	public int getPrevCpprox1002Flag() {
		return PrevCpprox1002Flag;
	}
	public void setPrevCpprox1002Flag(int prevCpprox1002Flag) {
		PrevCpprox1002Flag = prevCpprox1002Flag;
	}
	public float getPrevCost2003() {
		return PrevCost2003;
	}
	public void setPrevCost2003(float prevCost2003) {
		PrevCost2003 = prevCost2003;
	}
	public int getPrevApprox20003Flag() {
		return PrevApprox20003Flag;
	}
	public void setPrevApprox20003Flag(int prevApprox20003Flag) {
		PrevApprox20003Flag = prevApprox20003Flag;
	}
	public float getPrevCostCe1001() {
		return PrevCostCe1001;
	}
	public void setPrevCostCe1001(float prevCostCe1001) {
		PrevCostCe1001 = prevCostCe1001;
	}
	public int getPrevApprox1001CeFlag() {
		return PrevApprox1001CeFlag;
	}
	public void setPrevApprox1001CeFlag(int prevApprox1001CeFlag) {
		PrevApprox1001CeFlag = prevApprox1001CeFlag;
	}
	public float getPrevCostCe1002() {
		return PrevCostCe1002;
	}
	public void setPrevCostCe1002(float prevCostCe1002) {
		PrevCostCe1002 = prevCostCe1002;
	}
	public int getPrevApprox1002CeFlag() {
		return PrevApprox1002CeFlag;
	}
	public void setPrevApprox1002CeFlag(int prevApprox1002CeFlag) {
		PrevApprox1002CeFlag = prevApprox1002CeFlag;
	}
	public float getPrevCostCe2003() {
		return PrevCostCe2003;
	}
	public void setPrevCostCe2003(float prevCostCe2003) {
		PrevCostCe2003 = prevCostCe2003;
	}
	public int getPrevApprox20003CeFlag() {
		return PrevApprox20003CeFlag;
	}
	public void setPrevApprox20003CeFlag(int prevApprox20003CeFlag) {
		PrevApprox20003CeFlag = prevApprox20003CeFlag;
	}
	private int PrevQuantity2003Logic;
	private float PrevCost1001;
	private int PrevApprox1001Flag;
	private float PrevCost1002;
	private int PrevCpprox1002Flag;
	private float PrevCost2003;
	private int PrevApprox20003Flag;
	private float PrevCostCe1001;
	private int PrevApprox1001CeFlag;
	private float PrevCostCe1002;
	private int PrevApprox1002CeFlag;
	private float PrevCostCe2003;
	private int PrevApprox20003CeFlag;
	private int altMakeDefaultFlag;
	public int getAltMakeDefaultFlag() {
		return altMakeDefaultFlag;
	}
	public void setAltMakeDefaultFlag(int altMakeDefaultFlag) {
		this.altMakeDefaultFlag = altMakeDefaultFlag;
	}
	public int getGearBoxDefaultFlag() {
		return gearBoxDefaultFlag;
	}
	public void setGearBoxDefaultFlag(int gearBoxDefaultFlag) {
		this.gearBoxDefaultFlag = gearBoxDefaultFlag;
	}
	public int getTypeDefaultFlag() {
		return typeDefaultFlag;
	}
	public void setTypeDefaultFlag(int typeDefaultFlag) {
		this.typeDefaultFlag = typeDefaultFlag;
	}
	public int getMakeDefaultFlag() {
		return makeDefaultFlag;
	}
	public void setMakeDefaultFlag(int makeDefaultFlag) {
		this.makeDefaultFlag = makeDefaultFlag;
	}
	private int gearBoxDefaultFlag;
	private int typeDefaultFlag;
	private int makeDefaultFlag;
	private String category;
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	private String colValCdSym;
	public String getColValCdSym() {
		return colValCdSym;
	}
	public void setColValCdSym(String colValCdSym) {
		this.colValCdSym = colValCdSym;
	}
	public int getSubColValFlg() {
		return subColValFlg;
	}
	public void setSubColValFlg(int subColValFlg) {
		this.subColValFlg = subColValFlg;
	}
	public int getDefaultFlag15() {
		return defaultFlag15;
	}
	public void setDefaultFlag15(int defaultFlag15) {
		this.defaultFlag15 = defaultFlag15;
	}
	public int getDefaultFlag30() {
		return defaultFlag30;
	}
	public void setDefaultFlag30(int defaultFlag30) {
		this.defaultFlag30 = defaultFlag30;
	}
	public int getAddOnDiffDs() {
		return addOnDiffDs;
	}
	public void setAddOnDiffDs(int addOnDiffDs) {
		this.addOnDiffDs = addOnDiffDs;
	}
	public int getCalculateLinkFlag() {
		return calculateLinkFlag;
	}
	public void setCalculateLinkFlag(int calculateLinkFlag) {
		this.calculateLinkFlag = calculateLinkFlag;
	}
	public int getDelColFlag() {
		return delColFlag;
	}
	public void setDelColFlag(int delColFlag) {
		this.delColFlag = delColFlag;
	}
	public int getFrameId() {
		return frameId;
	}
	public void setFrameId(int frameId) {
		this.frameId = frameId;
	}
	public String getAltMake() {
		return altMake;
	}
	public void setAltMake(String altMake) {
		this.altMake = altMake;
	}
	public String getGearbox() {
		return gearbox;
	}
	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}
	public int getInstrId() {
		return instrId;
	}
	public void setInstrId(int instrId) {
		this.instrId = instrId;
	}
	public String getTypeOfGov() {
		return typeOfGov;
	}
	public void setTypeOfGov(String typeOfGov) {
		this.typeOfGov = typeOfGov;
	}
	public String getTurbCode() {
		return turbCode;
	}
	public void setTurbCode(String turbCode) {
		this.turbCode = turbCode;
	}
	public String getTurbCodeNm() {
		return turbCodeNm;
	}
	public void setTurbCodeNm(String turbCodeNm) {
		this.turbCodeNm = turbCodeNm;
	}
	public String getTypeOfInstr() {
		return typeOfInstr;
	}
	public void setTypeOfInstr(String typeOfInstr) {
		this.typeOfInstr = typeOfInstr;
	}
	public String getAddOnCostCol() {
		return addOnCostCol;
	}
	public void setAddOnCostCol(String addOnCostCol) {
		this.addOnCostCol = addOnCostCol;
	}
	public String getSubColValCd() {
		return subColValCd;
	}
	public void setSubColValCd(String subColValCd) {
		this.subColValCd = subColValCd;
	}
	public String getTypeOfOPanel() {
		return typeOfOPanel;
	}
	public void setTypeOfOPanel(String typeOfOPanel) {
		this.typeOfOPanel = typeOfOPanel;
	}
	private int subColValFlg;
	private int defaultFlag15;
	private int defaultFlag30;
	private int addOnDiffDs;
	private int calculateLinkFlag;
	private int delColFlag;
	private int frameId;
	private String altMake;
	private String gearbox;
	private int instrId;
	private String typeOfGov;
	private String turbCode;
	private String turbCodeNm;
	private String typeOfInstr;
	private String addOnCostCol;
	private String subColValCd;
	private String typeOfOPanel;
	private String eleTypeName;
	public String getEleTypeName() {
		return eleTypeName;
	}
	public void setEleTypeName(String eleTypeName) {
		this.eleTypeName = eleTypeName;
	}
	public int getPanelDependFlag() {
		return panelDependFlag;
	}
	public void setPanelDependFlag(int panelDependFlag) {
		this.panelDependFlag = panelDependFlag;
	}
	public int getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(int isEnable) {
		this.isEnable = isEnable;
	}
	private int panelDependFlag;
	private int isEnable;
	public float price15;
	public float price30;
	private int curId;
	public int getCurId() {
		return curId;
	}
	public void setCurId(int curId) {
		this.curId = curId;
	}
	public String getCurNm() {
		return curNm;
	}
	public void setCurNm(String curNm) {
		this.curNm = curNm;
	}
	public String getCurCd() {
		return curCd;
	}
	public void setCurCd(String curCd) {
		this.curCd = curCd;
	}
	public String getComrTextA() {
		return comrTextA;
	}
	public void setComrTextA(String comrTextA) {
		this.comrTextA = comrTextA;
	}
	public String getComrTextD() {
		return comrTextD;
	}
	public void setComrTextD(String comrTextD) {
		this.comrTextD = comrTextD;
	}
	public String getComrTextE() {
		return comrTextE;
	}
	public void setComrTextE(String comrTextE) {
		this.comrTextE = comrTextE;
	}
	public String getConversionRate() {
		return conversionRate;
	}
	public void setConversionRate(String conversionRate) {
		this.conversionRate = conversionRate;
	}
	private String curNm;
	private String curCd;
	private String comrTextA;
	private String comrTextD;
	private String comrTextE;
	private String conversionRate;
	public float getPrice30() {
		return price30;
	}
	public void setPrice30(float price30) {
		this.price30 = price30;
	}
	public float getPrice15() {
		return price15;
	}
	public void setPrice15(float price15) {
		this.price15 = price15;
	}
	public int getActiveNew() {
		return activeNew;
	}
	public void setActiveNew(int activeNew) {
		this.activeNew = activeNew;
	}
	public int activeNew;
	public String filterCd;
	public String getFilterCd() {
		return filterCd;
	}
	public void setFilterCd(String filterCd) {
		this.filterCd = filterCd;
	}
	public float discountPer;
	public float getDiscountPer() {
		return discountPer;
	}
	public void setDiscountPer(float discountPer) {
		this.discountPer = discountPer;
	}
	public float getNonDiscountCost() {
		return nonDiscountCost;
	}
	public void setNonDiscountCost(float nonDiscountCost) {
		this.nonDiscountCost = nonDiscountCost;
	}
	public float nonDiscountCost;
	public String fixedText;
	public String genInCd;
	public String getGenInCd() {
		return genInCd;
	}
	public void setGenInCd(String genInCd) {
		this.genInCd = genInCd;
	}
	public String getFixedText() {
		return fixedText;
	}
	public void setFixedText(String fixedText) {
		this.fixedText = fixedText;
	}
	public String  updateCode;
	public String getUpdateCode() {
		return updateCode;
	}
	public void setUpdateCode(String updateCode) {
		this.updateCode = updateCode;
	}
	public String assingedTo;
	public String getAssingedTo() {
		return assingedTo;
	}
	public void setAssingedTo(String assingedTo) {
		this.assingedTo = assingedTo;
	}
	public String getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String modifiedDate;
	public String  sectionCd;
	public String offerType;
	public String fixedExt1;
	public String getFixedExt1() {
		return fixedExt1;
	}
	public void setFixedExt1(String fixedExt1) {
		this.fixedExt1 = fixedExt1;
	}
	public String getFixedExt2() {
		return fixedExt2;
	}
	public void setFixedExt2(String fixedExt2) {
		this.fixedExt2 = fixedExt2;
	}
	public String getFixedExt3() {
		return fixedExt3;
	}
	public void setFixedExt3(String fixedExt3) {
		this.fixedExt3 = fixedExt3;
	}
	public String getFixedExt4() {
		return fixedExt4;
	}
	public void setFixedExt4(String fixedExt4) {
		this.fixedExt4 = fixedExt4;
	}
	public String getFixedExt5() {
		return fixedExt5;
	}
	public void setFixedExt5(String fixedExt5) {
		this.fixedExt5 = fixedExt5;
	}

	public String fixedExt2;
	public String fixedExt3;
	public String fixedExt4;
	public String fixedExt5;
	public String getOfferType() {
		return offerType;
	}
	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}
	public String getSectionCd() {
		return sectionCd;
	}
	public void setSectionCd(String sectionCd) {
		this.sectionCd = sectionCd;
	}

	public String altError;
	public String getAltError() {
		return altError;
	}
	public void setAltError(String altError) {
		this.altError = altError;
	}

	public int desItemOrderId;
	public int getDesItemOrderId() {
		return desItemOrderId;
	}
	public void setDesItemOrderId(int desItemOrderId) {
		this.desItemOrderId = desItemOrderId;
	}

	public float totalDboEleCost;
	public float getTotalDboEleCost() {
		return totalDboEleCost;
	}
	public void setTotalDboEleCost(float totalDboEleCost) {
		this.totalDboEleCost = totalDboEleCost;
	}
	public float getTotalDboCiCost() {
		return totalDboCiCost;
	}
	public void setTotalDboCiCost(float totalDboCiCost) {
		this.totalDboCiCost = totalDboCiCost;
	}

	public float totalDboCiCost;
	public float  totalMechCost;
	public float getTotalMechCost() {
		return totalMechCost;
	}
	public void setTotalMechCost(float totalMechCost) {
		this.totalMechCost = totalMechCost;
	}

	public String itemErrMessage;
	public String getItemErrMessage() {
		return itemErrMessage;
	}
	public void setItemErrMessage(String itemErrMessage) {
		this.itemErrMessage = itemErrMessage;
	}
	public float totalEle;
	public float getTotalEle() {
		return totalEle;
	}
	public void setTotalEle(float totalEle) {
		this.totalEle = totalEle;
	}
	public float getTotalCi() {
		return totalCi;
	}
	public void setTotalCi(float totalCi) {
		this.totalCi = totalCi;
	}

	public float totalCi;
	
	public float totalEleCost;
	public float getTotalEleCost() {
		return totalEleCost;
	}
	public void setTotalEleCost(float totalEleCost) {
		this.totalEleCost = totalEleCost;
	}
	public String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String name;
	 public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}

	private String filePath;
	 private String item;
	public List<DBOBean> getItemList() {
		return itemList;
	}
	public void setItemList(List<DBOBean> itemList) {
		this.itemList = itemList;
	}

	List<DBOBean>itemList= new ArrayList<>();
	private int errorCode;
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	private int conditionTableInput;
	public int getConditionTableInput() {
		return conditionTableInput;
	}
	public void setConditionTableInput(int conditionTableInput) {
		this.conditionTableInput = conditionTableInput;
	}

	private String categoryNm;
	public String getCategoryNm() {
		return categoryNm;
	}
	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}
	public String getSubCategoryNm() {
		return subCategoryNm;
	}
	public void setSubCategoryNm(String subCategoryNm) {
		this.subCategoryNm = subCategoryNm;
	}
	public String getScopeNm() {
		return scopeNm;
	}
	public void setScopeNm(String scopeNm) {
		this.scopeNm = scopeNm;
	}
	private int slNo;
	public int getSlNo() {
		return slNo;
	}
	public void setSlNo(int slNo) {
		this.slNo = slNo;
	}
	private String subCategoryNm;
	private String scopeNm;
	private int seqNo;
	private int uncheckFlag;
	public int getUncheckFlag() {
		return uncheckFlag;
	}
	public void setUncheckFlag(int uncheckFlag) {
		this.uncheckFlag = uncheckFlag;
	}
	public int getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(int seqNo) {
		this.seqNo = seqNo;
	}
	public int getSsId() {
		return ssId;
	}
	public void setSsId(int ssId) {
		this.ssId = ssId;
	}

	private int ssId;
	private String quant;
	public String getQuant() {
		return quant;
	}
	public void setQuant(String quant) {
		this.quant = quant;
	}

	private String subScopeCd;
	public String getSubScopeCd() {
		return subScopeCd;
	}
	public void setSubScopeCd(String subScopeCd) {
		this.subScopeCd = subScopeCd;
	}

	private String cooling;
	public String getCooling() {
		return cooling;
	}
	public void setCooling(String cooling) {
		this.cooling = cooling;
	}

	private String information;
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getFinalts() {
		return finalts;
	}
	public void setFinalts(String finalts) {
		this.finalts = finalts;
	}

	private String finalts;
	private String equivalent;
	public String getEquivalent() {
		return equivalent;
	}
	public void setEquivalent(String equivalent) {
		this.equivalent = equivalent;
	}

	private String test;
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	private int newDesItemFlag;
	public int getNewDesItemFlag() {
		return newDesItemFlag;
	}
	public void setNewDesItemFlag(int newDesItemFlag) {
		this.newDesItemFlag = newDesItemFlag;
	}
	public int getNewDesSubItemFlag() {
		return newDesSubItemFlag;
	}
	public void setNewDesSubItemFlag(int newDesSubItemFlag) {
		this.newDesSubItemFlag = newDesSubItemFlag;
	}

	private int newDesSubItemFlag;
	private String panelType;
	public String getPanelType() {
		return panelType;
	}
	public void setPanelType(String panelType) {
		this.panelType = panelType;
	}

	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	private String  auxSteamValue;
	public String getAuxSteamValue() {
		return auxSteamValue;
	}
	public void setAuxSteamValue(String auxSteamValue) {
		this.auxSteamValue = auxSteamValue;
	}

	private String fixedText1;
	public String getFixedText1() {
		return fixedText1;
	}
	public void setFixedText1(String fixedText1) {
		this.fixedText1 = fixedText1;
	}
	public String getFixedText2() {
		return fixedText2;
	}
	public void setFixedText2(String fixedText2) {
		this.fixedText2 = fixedText2;
	}
	public String getFixedText3() {
		return fixedText3;
	}
	public void setFixedText3(String fixedText3) {
		this.fixedText3 = fixedText3;
	}

	private String cirWater;
	public String getCirWater() {
		return cirWater;
	}
	public void setCirWater(String cirWater) {
		this.cirWater = cirWater;
	}

	private String itemNmPurity;
	public String getItemNmPurity() {
		return itemNmPurity;
	}
	public void setItemNmPurity(String itemNmPurity) {
		this.itemNmPurity = itemNmPurity;
	}
	public int getItemNmPurityId() {
		return itemNmPurityId;
	}
	public void setItemNmPurityId(int itemNmPurityId) {
		this.itemNmPurityId = itemNmPurityId;
	}

	private int itemNmPurityId;
	private String conductivity;
	public String getConductivity() {
		return conductivity;
	}
	public void setConductivity(String conductivity) {
		this.conductivity = conductivity;
	}

	private String recmd;
	public String getRecmd() {
		return recmd;
	}
	public void setRecmd(String recmd) {
		this.recmd = recmd;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}

	private String limit;
	private String identifier;
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	private String fixedText2;
	private String fixedText3;
	private String subItemNmAcPow;
	public String getSubItemNmAcPow() {
		return subItemNmAcPow;
	}
	public void setSubItemNmAcPow(String subItemNmAcPow) {
		this.subItemNmAcPow = subItemNmAcPow;
	}
	public int getSubItemNmAcPowId() {
		return subItemNmAcPowId;
	}
	public void setSubItemNmAcPowId(int subItemNmAcPowId) {
		this.subItemNmAcPowId = subItemNmAcPowId;
	}

	private  int subItemNmAcPowId;
	private String scfm;;
	public String getScfm() {
		return scfm;
	}
	public void setScfm(String scfm) {
		this.scfm = scfm;
	}
	public String getScfmValue() {
		return scfmValue;
	}
	public void setScfmValue(String scfmValue) {
		this.scfmValue = scfmValue;
	}

	private String scfmValue;
	private String subItemNmLubOil;
	public String getSubItemNmLubOil() {
		return subItemNmLubOil;
	}
	public void setSubItemNmLubOil(String subItemNmLubOil) {
		this.subItemNmLubOil = subItemNmLubOil;
	}
	public int getSubItemNmLubOilId() {
		return subItemNmLubOilId;
	}
	public void setSubItemNmLubOilId(int subItemNmLubOilId) {
		this.subItemNmLubOilId = subItemNmLubOilId;
	}
	public String getLubOilValue() {
		return lubOilValue;
	}
	public void setLubOilValue(String lubOilValue) {
		this.lubOilValue = lubOilValue;
	}

	private String subItemNmInstrCntrl;
	public String getSubItemNmInstrCntrl() {
		return subItemNmInstrCntrl;
	}
	public void setSubItemNmInstrCntrl(String subItemNmInstrCntrl) {
		this.subItemNmInstrCntrl = subItemNmInstrCntrl;
	}
	public int getSubItemNmInstrCntrlId() {
		return subItemNmInstrCntrlId;
	}
	public void setSubItemNmInstrCntrlId(int subItemNmInstrCntrlId) {
		this.subItemNmInstrCntrlId = subItemNmInstrCntrlId;
	}

	private int subItemNmInstrCntrlId;
	private int subItemNmLubOilId;
	private String lubOilValue;
	private String subItemNmFresh;
	public String getSubItemNmFresh() {
		return subItemNmFresh;
	}
	public void setSubItemNmFresh(String subItemNmFresh) {
		this.subItemNmFresh = subItemNmFresh;
	}
	public int getSubItemNmFreshId() {
		return subItemNmFreshId;
	}
	public void setSubItemNmFreshId(int subItemNmFreshId) {
		this.subItemNmFreshId = subItemNmFreshId;
	}

	private int subItemNmFreshId;
	private String subItemNmAuxSteam;
	public String getSubItemNmAuxSteam() {
		return subItemNmAuxSteam;
	}
	public void setSubItemNmAuxSteam(String subItemNmAuxSteam) {
		this.subItemNmAuxSteam = subItemNmAuxSteam;
	}
	public int getSubItemNmAuxStemId() {
		return subItemNmAuxStemId;
	}
	public void setSubItemNmAuxStemId(int subItemNmAuxStemId) {
		this.subItemNmAuxStemId = subItemNmAuxStemId;
	}

	private  int subItemNmAuxStemId;
	private String itemNmAux;
	public String getItemNmAux() {
		return itemNmAux;
	}
	public void setItemNmAux(String itemNmAux) {
		this.itemNmAux = itemNmAux;
	}
	public int getItemNmAuxId() {
		return itemNmAuxId;
	}
	public void setItemNmAuxId(int itemNmAuxId) {
		this.itemNmAuxId = itemNmAuxId;
	}

	private int itemNmAuxId;
	private String itemNmPerfHmbd;
	public String getItemNmPerfHmbd() {
		return itemNmPerfHmbd;
	}
	public void setItemNmPerfHmbd(String itemNmPerfHmbd) {
		this.itemNmPerfHmbd = itemNmPerfHmbd;
	}
	public int getItemNmPerfId() {
		return itemNmPerfId;
	}
	public void setItemNmPerfId(int itemNmPerfId) {
		this.itemNmPerfId = itemNmPerfId;
	}

	private int itemNmPerfId;
	private String paramCd;
	public String getParamCd() {
		return paramCd;
	}
	public void setParamCd(String paramCd) {
		this.paramCd = paramCd;
	}

	private String consumerId1;
	public String getConsumerId1() {
		return consumerId1;
	}
	public void setConsumerId1(String consumerId1) {
		this.consumerId1 = consumerId1;
	}

	private int consumerId;
	public int getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(int consumerId) {
		this.consumerId = consumerId;
	}
	public String getStartUp() {
		return startUp;
	}
	public void setStartUp(String startUp) {
		this.startUp = startUp;
	}
	public String getContinuous() {
		return continuous;
	}
	public void setContinuous(String continuous) {
		this.continuous = continuous;
	}
	public int getEditFlag() {
		return editFlag;
	}
	public void setEditFlag(int editFlag) {
		this.editFlag = editFlag;
	}

	private String startUp;
	private String  continuous;
	private int editFlag;
	private int noOfCondition;
	private int condTypeId;
	public int getCondTypeId() {
		return condTypeId;
	}
	public void setCondTypeId(int condTypeId) {
		this.condTypeId = condTypeId;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getVoltage() {
		return voltage;
	}
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}
	public String getFeeder() {
		return feeder;
	}
	public void setFeeder(String feeder) {
		this.feeder = feeder;
	}
	private String unitItemNm;
	public String getUnitItemNm() {
		return unitItemNm;
	}
	public void setUnitItemNm(String unitItemNm) {
		this.unitItemNm = unitItemNm;
	}
	public String getUnitItemCd() {
		return unitItemCd;
	}
	public void setUnitItemCd(String unitItemCd) {
		this.unitItemCd = unitItemCd;
	}

	private String unitItemCd;
	private String   itemType;
	private String speed;
	private String voltage;
	private String feeder;
	public int getNoOfCondition() {
		return noOfCondition;
	}
	public void setNoOfCondition(int noOfCondition) {
		this.noOfCondition = noOfCondition;
	}
	public int getNoOfBleed() {
		return noOfBleed;
	}
	public void setNoOfBleed(int noOfBleed) {
		this.noOfBleed = noOfBleed;
	}
	public int getNoOfExt() {
		return noOfExt;
	}
	public void setNoOfExt(int noOfExt) {
		this.noOfExt = noOfExt;
	}

	private int noOfBleed;
	private int noOfExt;
	private String paramNm;
	public String getParamNm() {
		return paramNm;
	}
	public void setParamNm(String paramNm) {
		this.paramNm = paramNm;
	}
	public String getUnitNm() {
		return unitNm;
	}
	public void setUnitNm(String unitNm) {
		this.unitNm = unitNm;
	}
	public int getCond1() {
		return cond1;
	}
	public void setCond1(int cond1) {
		this.cond1 = cond1;
	}
	public int getCond2() {
		return cond2;
	}
	public void setCond2(int cond2) {
		this.cond2 = cond2;
	}
	public int getCond3() {
		return cond3;
	}
	public void setCond3(int cond3) {
		this.cond3 = cond3;
	}
	public int getCond4() {
		return cond4;
	}
	public void setCond4(int cond4) {
		this.cond4 = cond4;
	}
	public int getCond5() {
		return cond5;
	}
	public void setCond5(int cond5) {
		this.cond5 = cond5;
	}
	public int getCond6() {
		return cond6;
	}
	public void setCond6(int cond6) {
		this.cond6 = cond6;
	}
	public int getCond7() {
		return cond7;
	}
	public void setCond7(int cond7) {
		this.cond7 = cond7;
	}
	public int getCond8() {
		return cond8;
	}
	public void setCond8(int cond8) {
		this.cond8 = cond8;
	}
	public int getCond9() {
		return cond9;
	}
	public void setCond9(int cond9) {
		this.cond9 = cond9;
	}
	public int getCond10() {
		return cond10;
	}
	public void setCond10(int cond10) {
		this.cond10 = cond10;
	}
	
	private String unitNm;
	private int cond1;
	private int cond2;
	private int cond3;
	private int cond4;
	private int cond5;
	private int cond6;
	private int cond7;
	private int cond8;
	private int cond9;
	private int cond10;
	
	private int paramId;
	
	public int getParamId() {
		return paramId;
	}
	public void setParamId(int paramId) {
		this.paramId = paramId;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}

	public String getHmbdImage() {
		return hmbdImage;
	}
	public void setHmbdImage(String hmbdImage) {
		this.hmbdImage = hmbdImage;
	}

	private int unitId;
	private int hmbdTableInput;
private String	hmbdImage;
	public int getHmbdTableInput() {
		return hmbdTableInput;
	}
	public void setHmbdTableInput(int hmbdTableInput) {
		this.hmbdTableInput = hmbdTableInput;
	}
	public int getNoOfConditions() {
		return noOfConditions;
	}
	public void setNoOfConditions(int noOfConditions) {
		this.noOfConditions = noOfConditions;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getGuaranteed() {
		return guaranteed;
	}
	public void setGuaranteed(String guaranteed) {
		this.guaranteed = guaranteed;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}

	private int noOfConditions;
	private String parameter;
	private String units;
	private String guaranteed;
	private String condition;
	private int unitItemId;
	public int getUnitItemId() {
		return unitItemId;
	}
	public void setUnitItemId(int unitItemId) {
		this.unitItemId = unitItemId;
	}
	public String getChptCd() {
		return chptCd;
	}
	public void setChptCd(String chptCd) {
		this.chptCd = chptCd;
	}
	public String getUnItemCd() {
		return unItemCd;
	}
	public void setUnItemCd(String unItemCd) {
		this.unItemCd = unItemCd;
	}
	public String getUnItemNm() {
		return unItemNm;
	}
	public void setUnItemNm(String unItemNm) {
		this.unItemNm = unItemNm;
	}

	private String chptCd;
	private String unItemCd;
	private String unItemNm;
	
	
	private int newColValFlag;
	
	public int getNewColValFlag() {
		return newColValFlag;
	}
	public void setNewColValFlag(int newColValFlag) {
		this.newColValFlag = newColValFlag;
	}
	public int getAddPrbFlag() {
		return addPrbFlag;
	}
	public void setAddPrbFlag(int addPrbFlag) {
		this.addPrbFlag = addPrbFlag;
	}

	private String tagNum;
	public String getTagNum() {
		return tagNum;
	}
	public void setTagNum(String tagNum) {
		this.tagNum = tagNum;
	}

	private String type;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	public int getTagNo() {
		return tagNo;
	}
	public void setTagNo(int tagNo) {
		this.tagNo = tagNo;
	}
	public String getEquipment() {
		return equipment;
	}
	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	private int approxFlag;
	public int getApproxFlag() {
		return approxFlag;
	}
	public void setApproxFlag(int approxFlag) {
		this.approxFlag = approxFlag;
	}
	private int addPrbFlag;
	private int orderBy;
private int tagNo;
private String equipment;
private String application;
	private String location;
	private String instrTypeNm;
	/**
	 * @return the instrTypeNm
	 */
	public String getInstrTypeNm() {
		return instrTypeNm;
	}
	/**
	 * @param instrTypeNm the instrTypeNm to set
	 */
	public void setInstrTypeNm(String instrTypeNm) {
		this.instrTypeNm = instrTypeNm;
	}
	/**
	 * @return the instrDesc
	 */
	public String getInstrDesc() {
		return instrDesc;
	}
	/**
	 * @param instrDesc the instrDesc to set
	 */
	public void setInstrDesc(String instrDesc) {
		this.instrDesc = instrDesc;
	}
	/**
	 * @return the instrMounting
	 */
	public String getInstrMounting() {
		return instrMounting;
	}
	/**
	 * @param instrMounting the instrMounting to set
	 */
	public void setInstrMounting(String instrMounting) {
		this.instrMounting = instrMounting;
	}
	/**
	 * @return the qtyLogic
	 */
	public String getQtyLogic() {
		return qtyLogic;
	}
	/**
	 * @param qtyLogic the qtyLogic to set
	 */
	public void setQtyLogic(String qtyLogic) {
		this.qtyLogic = qtyLogic;
	}
	
	
	public String getAddInstrNm() {
		return addInstrNm;
	}
	public void setAddInstrNm(String addInstrNm) {
		this.addInstrNm = addInstrNm;
	}

	private String  note;
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	private String addInstrNm;
	private String instrDesc;
	private String instrMounting;
	private String qtyLogic;
	private int catDetId;
	private int colId;
	private int colValId;
	private int costId;
	private int itemId;
	private int dboQuotDetId;
	private int addOnItemId;
	private int priceId;
	private int regionId;
	private int updateRequestNumber;
	private int assingedToId;
	private int statusId;
	private int otherCompId;
	private int generalAddOnId;
	private int createdById;
	private int addInstrId;
	private int orderId;
	private int defaultFlagNew;
	private int id;
	private int genInId;
	private int quotGenId;
	private int desItemId;
	private int desSubItemOrderId;
	private int mastQuotId;
	private int addOnFlg;
	private boolean isEnabled;
	private boolean genInputLink;
	private float extScopeRhsCost;
	private int rhsDispInd;
	private float extScopeCost;
	private float totalAddInstrCost;
	private String lhsColNm;
	
	public String getLhsColNm() {
		return lhsColNm;
	}
	public void setLhsColNm(String lhsColNm) {
		this.lhsColNm = lhsColNm;
	}
	public float getTotalAddInstrCost() {
		return totalAddInstrCost;
	}
	public void setTotalAddInstrCost(float totalAddInstrCost) {
		this.totalAddInstrCost = totalAddInstrCost;
	}

	private String instrCode;
	public String getInstrCode() {
		return instrCode;
	}
	public void setInstrCode(String instrCode) {
		this.instrCode = instrCode;
	}
	public String getInstrNm() {
		return instrNm;
	}
	public void setInstrNm(String instrNm) {
		this.instrNm = instrNm;
	}
	public int getQuantityLogic() {
		return quantityLogic;
	}
	public void setQuantityLogic(int quantityLogic) {
		this.quantityLogic = quantityLogic;
	}
	public int getNoOfTable() {
		return noOfTable;
	}
	public void setNoOfTable(int noOfTable) {
		this.noOfTable = noOfTable;
	}
	public int getInstrItemId() {
		return instrItemId;
	}
	public void setInstrItemId(int instrItemId) {
		this.instrItemId = instrItemId;
	}
	public String getInstrItemNm() {
		return instrItemNm;
	}
	public void setInstrItemNm(String instrItemNm) {
		this.instrItemNm = instrItemNm;
	}
	public int getInstrSubItemId() {
		return instrSubItemId;
	}
	public void setInstrSubItemId(int instrSubItemId) {
		this.instrSubItemId = instrSubItemId;
	}
	public String getInstrSubItemNm() {
		return instrSubItemNm;
	}
	public void setInstrSubItemNm(String instrSubItemNm) {
		this.instrSubItemNm = instrSubItemNm;
	}
	private String instrNm;
	private int quantityLogic;
	private int noOfTable;
	private int instrItemId;
	private String instrItemNm;
	private int instrSubItemId;
	private String instrSubItemNm;
	
	private float totalExtCost;
	private int colDispInd;
	private int colOrderIn;
	private float mechAuxTotalPrice;
	private String groupCode;
	private String typeOfPanel;
	private boolean EleTcpFilterFlag;
	public boolean isEleTcpFilterFlag() {
		return EleTcpFilterFlag;
	}
	public void setEleTcpFilterFlag(boolean eleTcpFilterFlag) {
		EleTcpFilterFlag = eleTcpFilterFlag;
	}
	public String getTypeOfPanel() {
		return typeOfPanel;
	}
	public void setTypeOfPanel(String typeOfPanel) {
		this.typeOfPanel = typeOfPanel;
	}
	private int vmsId;
	public int getVmsId() {
		return vmsId;
	}
	public void setVmsId(int vmsId) {
		this.vmsId = vmsId;
	}
	private String headerText;
	private String footerText;
	private String exclusionNm;
	public String getExclusionNm() {
		return exclusionNm;
	}
	public void setExclusionNm(String exclusionNm) {
		this.exclusionNm = exclusionNm;
	}
	private String  hearerNm;
	public String getHearerNm() {
		return hearerNm;
	}
	public void setHearerNm(String hearerNm) {
		this.hearerNm = hearerNm;
	}
	private String subColValCode;
	private String splColNm;
	private String  desSubItemName;
	private float addOnQuantity;
	private String colType;
	private String addOnCompNm;
	private String addInstrCompNm;
	private String RhsColcomments;
	private float price;
	private float addOnCost;
	private float totalPrice;
	private float overwrittenPrice;
	private float percentage;
	private float prevPercent;
	private float directPrice;
	private float prevDirectPrice;
	private float minVal;
	private float maxVal;
	private float userVal;
	private float noOfMonths;
	private float condensorFlowCapacity;
	private float prevPrice;
	private int quantity;
	private int dtFrmFlag;
	
	public int getDtFrmFlag() {
		return dtFrmFlag;
	}
	public void setDtFrmFlag(int dtFrmFlag) {
		this.dtFrmFlag = dtFrmFlag;
	}
	public int getCustTypeDependFlag() {
		return custTypeDependFlag;
	}
	public void setCustTypeDependFlag(int custTypeDependFlag) {
		this.custTypeDependFlag = custTypeDependFlag;
	}
	private int custTypeDependFlag;
	private float othQuantity;
	private float addInstrQuantity;
	private float addInstrCost;
	private float othPrice;
	private float additionalCost;
	private float inputCost;
	private float addOnCostPer;
	private float prevAddOnCostPer;
	private String prevErrMessage;
	public String getPrevErrMessage() {
		return prevErrMessage;
	}
	public void setPrevErrMessage(String prevErrMessage) {
		this.prevErrMessage = prevErrMessage;
	}
	public float getPrevAddOnCostPer() {
		return prevAddOnCostPer;
	}
	public void setPrevAddOnCostPer(float prevAddOnCostPer) {
		this.prevAddOnCostPer = prevAddOnCostPer;
	}
	public float getPrevAddOnDirCost() {
		return prevAddOnDirCost;
	}
	public void setPrevAddOnDirCost(float prevAddOnDirCost) {
		this.prevAddOnDirCost = prevAddOnDirCost;
	}
	private float prevAddOnDirCost;
	private float addOnDirCost;
	private String errMessage;
	public String getErrMessage() {
		return errMessage;
	}
	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}
	private String prevNote;
	public String getPrevNote() {
		return prevNote;
	}
	public void setPrevNote(String prevNote) {
		this.prevNote = prevNote;
	}
	private int approxCostFlag;
	private int prevApproxCostFlag;
	public int getPrevApproxCostFlag() {
		return prevApproxCostFlag;
	}
	public void setPrevApproxCostFlag(int prevApproxCostFlag) {
		this.prevApproxCostFlag = prevApproxCostFlag;
	}
	public int getApproxCostFlag() {
		return approxCostFlag;
	}
	public void setApproxCostFlag(int approxCostFlag) {
		this.approxCostFlag = approxCostFlag;
	}
	private int prevAddOnFlag;
	public int getPrevAddOnFlag() {
		return prevAddOnFlag;
	}
	public void setPrevAddOnFlag(int prevAddOnFlag) {
		this.prevAddOnFlag = prevAddOnFlag;
	}
	private int prevtAddOnDiffDs;
	public int getPrevtAddOnDiffDs() {
		return prevtAddOnDiffDs;
	}
	public void setPrevtAddOnDiffDs(int prevtAddOnDiffDs) {
		this.prevtAddOnDiffDs = prevtAddOnDiffDs;
	}
	private float splQuantity;
	private String splCompName;
	private String groupDescription;
	private float splCost;
	private String  subColValNm;
	private String  errorMsg;
	private String desItemName;
	private String compRemarks;
	private String itemCd;
	private String addInstrCd;
	private String prevAddInstrCd;
	private String addInstrRemarks;
	private String addInstrName;
	private String custCode;
	private String custType;
	private String colCd;
	private String colNm;
	private String colValCd;
	private String prevColValCd;
	private String colValNm;
	private String catDetCd;
	private String catDetDesc;
	private String itemName;
	private String priceCode;
	private String addOnName;
	private String remarks;
	private String techRemarks;
	private String comrRemarks;
	private String techComments;
	private String comrComments;
	private String panelCode;
	private String statusName;
	private String assignedTo;
	private String createdBy;
	private String updReqName;
	private String dboEleType;
	private String generalAddOnCode;
	private String generalAddOnName;
	private String othCompName;
	private String othRemarks;
	private String prevColCd;
	private String prevColNm;
	private String splAddonNm;
	private String f2fItem;
	private int itemApplInd;
	private int itemOrder;
	private String subItemCd;
	private String categoryValName;
	private String subItemName;
	private boolean subItemFlag;
	private int lhsFlag;
	public int getLhsFlag() {
		return lhsFlag;
	}
	public void setLhsFlag(int lhsFlag) {
		this.lhsFlag = lhsFlag;
	}

	private boolean rhsFlag;
	private int subItemApplInd;
	private int subItemOrder;
	private int subItemTypeId;
	private int mechItemId;
	private int f2fItemId;
	private int addOnOverwrittenFlag1;
	private String subItemTypeCd;
	private String subItemTypeName;
	private boolean subItemTypeFlag;
	private String rhsColTechcomments;
	private String rhsColComrcomments;
	private String genType;
	private int genInNo;
	private String genInNm;
	private boolean addOnFlag;
	private int eleFilterId;
	private String eleType;
	private int filterId;
	private int quotFilterId;
	private String scopeCode;
	private String dboMechItem;
	private int pkgQuotId;
	private int packageTypeId;
	private String packageTypeName;
	private String packageTypeCode;
	private int framePowerId;
	private String bgmType;
	public String getBgmType() {
		return bgmType;
	}
	public void setBgmType(String bgmType) {
		this.bgmType = bgmType;
	}
	public float getBgmRating() {
		return bgmRating;
	}
	public void setBgmRating(float bgmRating) {
		this.bgmRating = bgmRating;
	}
	private float bgmRating;
	private String frameName;
	private int condensingTypeId;
	private String condensingTypeName;
	private String frameDesc;
	private String condensingType;
	private int typeOfChargeId;
	private String typeOfCharge;
	private String typeOfChargeCd;
	private int loadingId;
	private String loadingName;
	private String loadingCd;
	private int lodgingId;
	private String lodgingName;
	private String lodgingCd;
	private int  noOfManDays;
	private String subItemType;
//	private boolean techFlag;
	private int techFlag;
	private int ComrFlag;
	private String desColOrderId;
	public String getDesColOrderId() {
		return desColOrderId;
	}
	public void setDesColOrderId(String desColOrderId) {
		this.desColOrderId = desColOrderId;
	}
	private int dispInd;
//	private boolean ComrFlag;
	private boolean OthColFlag;
	private boolean CalcLinkFlag;
	
	private boolean DefaultFlag;
	private boolean PercentFlag;
	private boolean DirPriceFlag;
	private boolean newPanelFlag;
	private float Percent;
	private float dirPrice;
	private int eleSpecialId;
	
	private int eleItemId;
	private boolean linkFlag;
	private boolean dependFlag;
	private boolean f2fAddOnFlag;
	
	private Date modDt;
	private int desSubItemId;
	private boolean subColValFlag;
	private String subColValName;
	private String make;
	private String  disableColValCd;
	
	private boolean isActive;
	private boolean isDone;
	private boolean quantityFlag;
	private boolean stdPriceFlag;
	private boolean colTypeFlag;
	private boolean overwrittenPriceFlag;
	private boolean percentageFlag;
	private boolean addOnListFlag;
//	private boolean splAddonFlag;
	private boolean defaultVal;
	private boolean prevDefaultVal;
	private boolean excelFlag;
	private boolean sapFlag;
	private boolean othersFlag;
	private boolean addOnOthersFlag;
	private boolean addOnOverwrittenFlag;
	private boolean directPriceFlag;
//	private boolean othColValFlag;
	private int othColValFlag;
	private int addOnNewColFlag;
	private int inputCostFlag;
//	private boolean addOnCostMeFlag;
	private int addOnCostMeFlag;
	private int transMastId;
	private int transTypeId;
	private String transType;
	private String fOBPlace;
	private int compoId;
	private String compoName;
	private int numberOfVehicle;
	private String toPlace;
	private int distance;
	private int portId;
	private String countryName;
	private String portName;
	private float compPrice;
	private float fobPrice;
	private int otherItemId;
	private float insurance;
	private int  inTravelExpensesReq;
	private int inNoOfVisit;
	private int inCostForEachVisit;
	private float splProvision;
	private float travelExpenses;
	private float turbineContigency;
	private float dboContigency;
	private float inpAgencyCommission;
	private float salesExpenses;
	private float engineCharges;
	private float  intrestNoOfMonths;
	private float  others;
	private String othersRemarks;
	private float warrantyPeriod;
	private float varProfit;
	private float inpLdforLateDelivery;
	private float inpLdPerformance;
	private float intrestPercentage;
	private float inpBG1;
	private float inpBG2;
	private float  totOrderCost;
	private float contigencyGeneral;
	private float agencyCommCharges;
	private float ldPerformance;
	private float ldforLateDelivery;
	private float bankingCharges1;
	private float bankingCharges2;
	private float ovrheadSaleCost;
	private float overRawMaterialCost;
	private float  warrantyCost;
	private float intrestCharges;
	private float totalVariableCost;
	private boolean varNewFlag;
	private float varNewCost;
	private float projSupply;
	private float  projServices;
	private float projTransport;
	private float totalProjectCost;
	private float  supplyCost;
	private float serviceCost;
	private float transCost;
	private float totOrderCostNetProfit;
	private float percentProfit;
	private float turbineOrderBookCost;
	private float totalFtfCost;
	private float  packageCost;
	private float dboMechCost;
	private float dboEleCost;
	private float  projectTotalCost;
	private boolean projectNewFlag;
	private int itemApproxCostFlag;
	private float  projectNewCost;
	
	
	public int getItemApproxCostFlag() {
		return itemApproxCostFlag;
	}
	public void setItemApproxCostFlag(int itemApproxCostFlag) {
		this.itemApproxCostFlag = itemApproxCostFlag;
	}

	private SelectBox dropDownType = new SelectBox();
	private List<SelectBox> dropDownValueList =new ArrayList<>();
	private int subItemId;
	private int quotId;
	private String comments;
	private boolean splAddonFlag;
	private String itemNm;
	private String subItemNm;
	private String othColNm;
	private float cost;
	private float othersCost;
	private float itemCost;
	private float basicCost;
	private int detQuotId;
	private float rhsCost;
	private float rhsColQuantity;
	private float subItemQuantity;
	private float subItemCost;
	private String subItemTechRemarks;
	private String subItemComrRemarks;
	
	private float cITotalExtCost;
	public float getcITotalExtCost() {
		return cITotalExtCost;
	}
	public void setcITotalExtCost(float cITotalExtCost) {
		this.cITotalExtCost = cITotalExtCost;
	}
	public String getcITechComments() {
		return cITechComments;
	}
	public void setcITechComments(String cITechComments) {
		this.cITechComments = cITechComments;
	}
	public String getcIComrComments() {
		return cIComrComments;
	}
	public void setcIComrComments(String cIComrComments) {
		this.cIComrComments = cIComrComments;
	}
	private float eleTotalExtCost;
	public float getEleTotalExtCost() {
		return eleTotalExtCost;
	}
	public void setEleTotalExtCost(float eleTotalExtCost) {
		this.eleTotalExtCost = eleTotalExtCost;
	}
	public String getEleTechComments() {
		return eleTechComments;
	}
	public void setEleTechComments(String eleTechComments) {
		this.eleTechComments = eleTechComments;
	}
	public String getEleComrComments() {
		return eleComrComments;
	}
	public void setEleComrComments(String eleComrComments) {
		this.eleComrComments = eleComrComments;
	}
	private float mechTotalExtCost;
	public float getMechTotalExtCost() {
		return mechTotalExtCost;
	}
	public void setMechTotalExtCost(float mechTotalExtCost) {
		this.mechTotalExtCost = mechTotalExtCost;
	}
	public String getMechTechComments() {
		return mechTechComments;
	}
	public void setMechTechComments(String mechTechComments) {
		this.mechTechComments = mechTechComments;
	}
	public String getMechComrComments() {
		return mechComrComments;
	}
	public void setMechComrComments(String mechComrComments) {
		this.mechComrComments = mechComrComments;
	}

	private String mechTechComments;
	private String mechComrComments;
	private String eleTechComments;
	private String  eleComrComments;
	private String cITechComments;
	private String cIComrComments;
	private float subItemTypeQuantity;
	private float subItemTypeCost;
	private String subItemTypeTechRemarks;
	private String subItemTypeComrRemarks;
	private boolean itemFlag;
	public boolean isItemFlag() {
		return itemFlag;
	}
	public void setItemFlag(boolean itemFlag) {
		this.itemFlag = itemFlag;
	}
	public boolean isAddInstrFlag() {
		return addInstrFlag;
	}
	public void setAddInstrFlag(boolean addInstrFlag) {
		this.addInstrFlag = addInstrFlag;
	}
	private float prevMinVal;
	public float getPrevMinVal() {
		return prevMinVal;
	}
	public void setPrevMinVal(float prevMinVal) {
		this.prevMinVal = prevMinVal;
	}
	public float getPrevMaxVal() {
		return prevMaxVal;
	}
	public void setPrevMaxVal(float prevMaxVal) {
		this.prevMaxVal = prevMaxVal;
	}
	private int prevQuantity;
	public int getPrevQuantity() {
		return prevQuantity;
	}
	public void setPrevQuantity(int prevQuantity) {
		this.prevQuantity = prevQuantity;
	}
	private float prevMaxVal;
	private boolean addInstrFlag;
	private String colValComments;
	private String categoryValCode;
	private String subItemCode;
	private String scopeCd;
	
//	bean.setRhsCost(Math.round(resultSetData2.getFloat("RHS_COST")));
//	bean.setRhsColQuantity(resultSetData2.getFloat("RHS_COL_QTY"));
//	bean.setRhsColComments(resultSetData2.getString("RHS_COL_COMMENTS"));
//	
	
	
	
	public String getScopeCd() {
		return scopeCd;
	}
	public void setScopeCd(String scopeCd) {
		this.scopeCd = scopeCd;
	}
	/**
	 * @return the catDetId
	 */
	public int getCatDetId() {
		return catDetId;
	}
	/**
	 * @param catDetId the catDetId to set
	 */
	public void setCatDetId(int catDetId) {
		this.catDetId = catDetId;
	}
	/**
	 * @return the catDetCd
	 */
	public String getCatDetCd() {
		return catDetCd;
	}
	/**
	 * @param catDetCd the catDetCd to set
	 */
	public void setCatDetCd(String catDetCd) {
		this.catDetCd = catDetCd;
	}
	/**
	 * @return the catDetDesc
	 */
	public String getCatDetDesc() {
		return catDetDesc;
	}
	/**
	 * @param catDetDesc the catDetDesc to set
	 */
	public void setCatDetDesc(String catDetDesc) {
		this.catDetDesc = catDetDesc;
	}
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the colCd
	 */
	public String getColCd() {
		return colCd;
	}
	/**
	 * @param colCd the colCd to set
	 */
	public void setColCd(String colCd) {
		this.colCd = colCd;
	}
	/**
	 * @return the colNm
	 */
	public String getColNm() {
		return colNm;
	}
	/**
	 * @param colNm the colNm to set
	 */
	public void setColNm(String colNm) {
		this.colNm = colNm;
	}
	/**
	 * @return the colValCd
	 */
	public String getColValCd() {
		return colValCd;
	}
	/**
	 * @param colValCd the colValCd to set
	 */
	public void setColValCd(String colValCd) {
		this.colValCd = colValCd;
	}
	/**
	 * @return the colValNm
	 */
	public String getColValNm() {
		return colValNm;
	}
	/**
	 * @param colValNm the colValNm to set
	 */
	public void setColValNm(String colValNm) {
		this.colValNm = colValNm;
	}
	/**
	 * @return the colId
	 */
	public int getColId() {
		return colId;
	}
	/**
	 * @param colId the colId to set
	 */
	public void setColId(int colId) {
		this.colId = colId;
	}
	
	/**
	 * @return the dropDownType
	 */
	public SelectBox getDropDownType() {
		return dropDownType;
	}
	/**
	 * @param dropDownType the dropDownType to set
	 */
	public void setDropDownType(SelectBox dropDownType) {
		this.dropDownType = dropDownType;
	}
	/**
	 * @return the dropDownValueList
	 */
	public List<SelectBox> getDropDownValueList() {
		return dropDownValueList;
	}
	/**
	 * @param dropDownValueList the dropDownValueList to set
	 */
	public void setDropDownValueList(List<SelectBox> dropDownValueList) {
		this.dropDownValueList = dropDownValueList;
	}
	
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/**
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}
	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	/**
	 * @return the priceCode
	 */
	public String getPriceCode() {
		return priceCode;
	}
	/**
	 * @param priceCode the priceCode to set
	 */
	public void setPriceCode(String priceCode) {
		this.priceCode = priceCode;
	}
	/**
	 * @return the dboQuotDetId
	 */
	public int getDboQuotDetId() {
		return dboQuotDetId;
	}
	/**
	 * @param dboQuotDetId the dboQuotDetId to set
	 */
	public void setDboQuotDetId(int dboQuotDetId) {
		this.dboQuotDetId = dboQuotDetId;
	}
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the percentage
	 */
	public float getPercentage() {
		return percentage;
	}
	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}
	
	/**
	 * @return the stdPriceFlag
	 */
	public boolean isStdPriceFlag() {
		return stdPriceFlag;
	}
	/**
	 * @param stdPriceFlag the stdPriceFlag to set
	 */
	public void setStdPriceFlag(boolean stdPriceFlag) {
		this.stdPriceFlag = stdPriceFlag;
	}
	/**
	 * @return the directPrice
	 */
	public float getDirectPrice() {
		return directPrice;
	}
	/**
	 * @param directPrice the directPrice to set
	 */
	public void setDirectPrice(float directPrice) {
		this.directPrice = directPrice;
	}
	/**
	 * @return the percentageFlag
	 */
	public boolean isPercentageFlag() {
		return percentageFlag;
	}
	/**
	 * @param percentageFlag the percentageFlag to set
	 */
	public void setPercentageFlag(boolean percentageFlag) {
		this.percentageFlag = percentageFlag;
	}
	/**
	 * @return the minVal
	 */
	public float getMinVal() {
		return minVal;
	}
	/**
	 * @param minVal the minVal to set
	 */
	public void setMinVal(float minVal) {
		this.minVal = minVal;
	}
	/**
	 * @return the maxVal
	 */
	public float getMaxVal() {
		return maxVal;
	}
	/**
	 * @param maxVal the maxVal to set
	 */
	public void setMaxVal(float maxVal) {
		this.maxVal = maxVal;
	}
	/**
	 * @return the addOnListFlag
	 */
	public boolean isAddOnListFlag() {
		return addOnListFlag;
	}
	/**
	 * @param addOnListFlag the addOnListFlag to set
	 */
	public void setAddOnListFlag(boolean addOnListFlag) {
		this.addOnListFlag = addOnListFlag;
	}
	/**
	 * @return the splAddonFlag
	 */
	public boolean isSplAddonFlag() {
		return splAddonFlag;
	}
	/**
	 * @param splAddonFlag the splAddonFlag to set
	 */
	public void setSplAddonFlag(boolean splAddonFlag) {
		this.splAddonFlag = splAddonFlag;
	}
	/**
	 * @return the defaultVal
	 */
	public boolean isDefaultVal() {
		return defaultVal;
	}
	/**
	 * @param defaultVal the defaultVal to set
	 */
	public void setDefaultVal(boolean defaultVal) {
		this.defaultVal = defaultVal;
	}
	/**
	 * @return the excelFlag
	 */
	public boolean isExcelFlag() {
		return excelFlag;
	}
	/**
	 * @param excelFlag the excelFlag to set
	 */
	public void setExcelFlag(boolean excelFlag) {
		this.excelFlag = excelFlag;
	}
	/**
	 * @return the sapFlag
	 */
	public boolean isSapFlag() {
		return sapFlag;
	}
	/**
	 * @param sapFlag the sapFlag to set
	 */
	public void setSapFlag(boolean sapFlag) {
		this.sapFlag = sapFlag;
	}
	/**
	 * @return the directPriceFlag
	 */
	public boolean isDirectPriceFlag() {
		return directPriceFlag;
	}
	/**
	 * @param directPriceFlag the directPriceFlag to set
	 */
	public void setDirectPriceFlag(boolean directPriceFlag) {
		this.directPriceFlag = directPriceFlag;
	}
	/**
	 * @return the addOnItemId
	 */
	public int getAddOnItemId() {
		return addOnItemId;
	}
	/**
	 * @param addOnItemId the addOnItemId to set
	 */
	public void setAddOnItemId(int addOnItemId) {
		this.addOnItemId = addOnItemId;
	}
	/**
	 * @return the addOnName
	 */
	public String getAddOnName() {
		return addOnName;
	}
	/**
	 * @param addOnName the addOnName to set
	 */
	public void setAddOnName(String addOnName) {
		this.addOnName = addOnName;
	}
	/**
	 * @return the userVal
	 */
	public float getUserVal() {
		return userVal;
	}
	/**
	 * @param userVal the userVal to set
	 */
	public void setUserVal(float userVal) {
		this.userVal = userVal;
	}
	/**
	 * @return the overwrittenPrice
	 */
	public float getOverwrittenPrice() {
		return overwrittenPrice;
	}
	/**
	 * @param overwrittenPrice the overwrittenPrice to set
	 */
	public void setOverwrittenPrice(float overwrittenPrice) {
		this.overwrittenPrice = overwrittenPrice;
	}
	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}
	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * @return the overwrittenPriceFlag
	 */
	public boolean isOverwrittenPriceFlag() {
		return overwrittenPriceFlag;
	}
	/**
	 * @param overwrittenPriceFlag the overwrittenPriceFlag to set
	 */
	public void setOverwrittenPriceFlag(boolean overwrittenPriceFlag) {
		this.overwrittenPriceFlag = overwrittenPriceFlag;
	}
	/**
	 * @return the condensorFlowCapacity
	 */
	public float getCondensorFlowCapacity() {
		return condensorFlowCapacity;
	}
	/**
	 * @param condensorFlowCapacity the condensorFlowCapacity to set
	 */
	public void setCondensorFlowCapacity(float condensorFlowCapacity) {
		this.condensorFlowCapacity = condensorFlowCapacity;
	}
	/**
	 * @return the itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}
	/**
	 * @param itemCd the itemCd to set
	 */
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
	}
	/**
	 * @return the noOfMonths
	 */
	public float getNoOfMonths() {
		return noOfMonths;
	}
	/**
	 * @param noOfMonths the noOfMonths to set
	 */
	public void setNoOfMonths(float noOfMonths) {
		this.noOfMonths = noOfMonths;
	}
	/**
	 * @return the panelCode
	 */
	public String getPanelCode() {
		return panelCode;
	}
	/**
	 * @param panelCode the panelCode to set
	 */
	public void setPanelCode(String panelCode) {
		this.panelCode = panelCode;
	}
	/**
	 * @return the costId
	 */
	public int getCostId() {
		return costId;
	}
	/**
	 * @param costId the costId to set
	 */
	public void setCostId(int costId) {
		this.costId = costId;
	}
	/**
	 * @return the priceId
	 */
	public int getPriceId() {
		return priceId;
	}
	/**
	 * @param priceId the priceId to set
	 */
	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}
	/**
	 * @return the updateRequestNumber
	 */
	public int getUpdateRequestNumber() {
		return updateRequestNumber;
	}
	/**
	 * @param updateRequestNumber the updateRequestNumber to set
	 */
	public void setUpdateRequestNumber(int updateRequestNumber) {
		this.updateRequestNumber = updateRequestNumber;
	}
	/**
	 * @return the colValId
	 */
	public int getColValId() {
		return colValId;
	}
	/**
	 * @param colValId the colValId to set
	 */
	public void setColValId(int colValId) {
		this.colValId = colValId;
	}
	/**
	 * @return the prevPercent
	 */
	public float getPrevPercent() {
		return prevPercent;
	}
	/**
	 * @param prevPercent the prevPercent to set
	 */
	public void setPrevPercent(float prevPercent) {
		this.prevPercent = prevPercent;
	}
	/**
	 * @return the prevDirectPrice
	 */
	public float getPrevDirectPrice() {
		return prevDirectPrice;
	}
	/**
	 * @param prevDirectPrice the prevDirectPrice to set
	 */
	public void setPrevDirectPrice(float prevDirectPrice) {
		this.prevDirectPrice = prevDirectPrice;
	}
	/**
	 * @return the assingedToId
	 */
	public int getAssingedToId() {
		return assingedToId;
	}
	/**
	 * @param assingedToId the assingedToId to set
	 */
	public void setAssingedToId(int assingedToId) {
		this.assingedToId = assingedToId;
	}
	/**
	 * @return the createdById
	 */
	public int getCreatedById() {
		return createdById;
	}
	/**
	 * @param createdById the createdById to set
	 */
	public void setCreatedById(int createdById) {
		this.createdById = createdById;
	}
	/**
	 * @return the statusName
	 */
	public String getStatusName() {
		return statusName;
	}
	/**
	 * @param statusName the statusName to set
	 */
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	/**
	 * @return the assignedTo
	 */
	public String getAssignedTo() {
		return assignedTo;
	}
	/**
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}
	/**
	 * @return the modDt
	 */
	public Date getModDt() {
		return modDt;
	}
	/**
	 * @param modDt the modDt to set
	 */
	public void setModDt(Date modDt) {
		this.modDt = modDt;
	}
	/**
	 * @return the statusId
	 */
	public int getStatusId() {
		return statusId;
	}
	/**
	 * @param statusId the statusId to set
	 */
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the updReqName
	 */
	public String getUpdReqName() {
		return updReqName;
	}
	/**
	 * @param updReqName the updReqName to set
	 */
	public void setUpdReqName(String updReqName) {
		this.updReqName = updReqName;
	}
	/**
	 * @return the prevPrice
	 */
	public float getPrevPrice() {
		return prevPrice;
	}
	/**
	 * @param prevPrice the prevPrice to set
	 */
	public void setPrevPrice(float prevPrice) {
		this.prevPrice = prevPrice;
	}
	/**
	 * @return the custCode
	 */
	public String getCustCode() {
		return custCode;
	}
	/**
	 * @param custCode the custCode to set
	 */
	public void setCustCode(String custCode) {
		this.custCode = custCode;
	}
	/**
	 * @return the totalPrice
	 */
	public float getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @return the othersFlag
	 */
	public boolean isOthersFlag() {
		return othersFlag;
	}
	/**
	 * @param othersFlag the othersFlag to set
	 */
	public void setOthersFlag(boolean othersFlag) {
		this.othersFlag = othersFlag;
	}
	
	/**
	 * @return the dboEleType
	 */
	public String getDboEleType() {
		return dboEleType;
	}
	/**
	 * @param dboEleType the dboEleType to set
	 */
	public void setDboEleType(String dboEleType) {
		this.dboEleType = dboEleType;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the regionId
	 */
	public int getRegionId() {
		return regionId;
	}
	/**
	 * @param regionId the regionId to set
	 */
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	/**
	 * @return the generalAddOnCode
	 */
	public String getGeneralAddOnCode() {
		return generalAddOnCode;
	}
	/**
	 * @param generalAddOnCode the generalAddOnCode to set
	 */
	public void setGeneralAddOnCode(String generalAddOnCode) {
		this.generalAddOnCode = generalAddOnCode;
	}
	/**
	 * @return the generalAddOnName
	 */
	public String getGeneralAddOnName() {
		return generalAddOnName;
	}
	/**
	 * @param generalAddOnName the generalAddOnName to set
	 */
	public void setGeneralAddOnName(String generalAddOnName) {
		this.generalAddOnName = generalAddOnName;
	}
	/**
	 * @return the quantityFlag
	 */
	public boolean isQuantityFlag() {
		return quantityFlag;
	}
	/**
	 * @param quantityFlag the quantityFlag to set
	 */
	public void setQuantityFlag(boolean quantityFlag) {
		this.quantityFlag = quantityFlag;
	}
	/**
	 * @return the othQuantity
	 */
	public float getOthQuantity() {
		return othQuantity;
	}
	/**
	 * @param othQuantity the othQuantity to set
	 */
	public void setOthQuantity(float othQuantity) {
		this.othQuantity = othQuantity;
	}
	/**
	 * @return the othPrice
	 */
	public float getOthPrice() {
		return othPrice;
	}
	/**
	 * @param othPrice the othPrice to set
	 */
	public void setOthPrice(float othPrice) {
		this.othPrice = othPrice;
	}
	/**
	 * @return the othCompName
	 */
	public String getOthCompName() {
		return othCompName;
	}
	/**
	 * @param othCompName the othCompName to set
	 */
	public void setOthCompName(String othCompName) {
		this.othCompName = othCompName;
	}
	/**
	 * @return the othRemarks
	 */
	public String getOthRemarks() {
		return othRemarks;
	}
	/**
	 * @param othRemarks the othRemarks to set
	 */
	public void setOthRemarks(String othRemarks) {
		this.othRemarks = othRemarks;
	}
	/**
	 * @return the otherCompId
	 */
	public int getOtherCompId() {
		return otherCompId;
	}
	/**
	 * @param otherCompId the otherCompId to set
	 */
	public void setOtherCompId(int otherCompId) {
		this.otherCompId = otherCompId;
	}
	/**
	 * @return the generalAddOnId
	 */
	public int getGeneralAddOnId() {
		return generalAddOnId;
	}
	/**
	 * @param generalAddOnId the generalAddOnId to set
	 */
	public void setGeneralAddOnId(int generalAddOnId) {
		this.generalAddOnId = generalAddOnId;
	}
	/**
	 * @return the addInstrId
	 */
	public int getAddInstrId() {
		return addInstrId;
	}
	/**
	 * @param addInstrId the addInstrId to set
	 */
	public void setAddInstrId(int addInstrId) {
		this.addInstrId = addInstrId;
	}
	/**
	 * @return the addInstrCd
	 */
	public String getAddInstrCd() {
		return addInstrCd;
	}
	/**
	 * @param addInstrCd the addInstrCd to set
	 */
	public void setAddInstrCd(String addInstrCd) {
		this.addInstrCd = addInstrCd;
	}
	/**
	 * @return the addInstrName
	 */
	public String getAddInstrName() {
		return addInstrName;
	}
	/**
	 * @param addInstrName the addInstrName to set
	 */
	public void setAddInstrName(String addInstrName) {
		this.addInstrName = addInstrName;
	}
	/**
	 * @return the addInstrQuantity
	 */
	public float getAddInstrQuantity() {
		return addInstrQuantity;
	}
	/**
	 * @param addInstrQuantity the addInstrQuantity to set
	 */
	public void setAddInstrQuantity(float addInstrQuantity) {
		this.addInstrQuantity = addInstrQuantity;
	}
	/**
	 * @return the addInstrCost
	 */
	public float getAddInstrCost() {
		return addInstrCost;
	}
	/**
	 * @param addInstrCost the addInstrCost to set
	 */
	public void setAddInstrCost(float addInstrCost) {
		this.addInstrCost = addInstrCost;
	}
	/**
	 * @return the addInstrRemarks
	 */
	public String getAddInstrRemarks() {
		return addInstrRemarks;
	}
	/**
	 * @param addInstrRemarks the addInstrRemarks to set
	 */
	public void setAddInstrRemarks(String addInstrRemarks) {
		this.addInstrRemarks = addInstrRemarks;
	}
	/**
	 * @return the additionalCost
	 */
	public float getAdditionalCost() {
		return additionalCost;
	}
	/**
	 * @param additionalCost the additionalCost to set
	 */
	public void setAdditionalCost(float additionalCost) {
		this.additionalCost = additionalCost;
	}
	/**
	 * @return the compRemarks
	 */
	public String getCompRemarks() {
		return compRemarks;
	}
	/**
	 * @param compRemarks the compRemarks to set
	 */
	public void setCompRemarks(String compRemarks) {
		this.compRemarks = compRemarks;
	}
	/**
	 * @return the addOnOthersFlag
	 */
	public boolean isAddOnOthersFlag() {
		return addOnOthersFlag;
	}
	/**
	 * @param addOnOthersFlag the addOnOthersFlag to set
	 */
	public void setAddOnOthersFlag(boolean addOnOthersFlag) {
		this.addOnOthersFlag = addOnOthersFlag;
	}
	/**
	 * @return the addOnOverwrittenFlag
	 */
	public boolean isAddOnOverwrittenFlag() {
		return addOnOverwrittenFlag;
	}
	/**
	 * @param addOnOverwrittenFlag the addOnOverwrittenFlag to set
	 */
	public void setAddOnOverwrittenFlag(boolean addOnOverwrittenFlag) {
		this.addOnOverwrittenFlag = addOnOverwrittenFlag;
	}
	public void setSubItemId(int subItemId) {
		this.subItemId = subItemId;
	}
	/**
	 * @return the priceId
	 */
	public int getSubItemId() {
		return subItemId;
	}
	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}
	/**
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}
	
	/**
	 * @return the prevColNm
	 */
	public String getPrevColNm() {
		return prevColNm;
	}
	/**
	 * @param prevColNm the prevColNm to set
	 */
	public void setPrevColNm(String prevColNm) {
		this.prevColNm = prevColNm;
	}
	/**
	 * @return the prevColCd
	 */
	/**
	 * @return the prevColCd
	 */
	public String getPrevColCd() {
		return prevColCd;
	}
	/**
	 * @param prevColCd the prevColCd to set
	 */
	public void setPrevColCd(String prevColCd) {
		this.prevColCd = prevColCd;
	}
	/**
	 * @return the prevAddInstrCd
	 */
	public String getPrevAddInstrCd() {
		return prevAddInstrCd;
	}
	/**
	 * @param prevAddInstrCd the prevAddInstrCd to set
	 */
	public void setPrevAddInstrCd(String prevAddInstrCd) {
		this.prevAddInstrCd = prevAddInstrCd;
	}
	/**
	 * @return the isDone
	 */
	public boolean isDone() {
		return isDone;
	}
	/**
	 * @param isDone the isDone to set
	 */
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	/**
	 * @return the prevColValCd
	 */
	public String getPrevColValCd() {
		return prevColValCd;
	}
	/**
	 * @param prevColValCd the prevColValCd to set
	 */
	public void setPrevColValCd(String prevColValCd) {
		this.prevColValCd = prevColValCd;
	}
	/**
	 * @return the prevDefaultVal
	 */
	public boolean isPrevDefaultVal() {
		return prevDefaultVal;
	}
	/**
	 * @param prevDefaultVal the prevDefaultVal to set
	 */
	public void setPrevDefaultVal(boolean prevDefaultVal) {
		this.prevDefaultVal = prevDefaultVal;
	}
	/**
	 * @return the splAddonNm
	 */
	public String getSplAddonNm() {
		return splAddonNm;
	}
	/**
	 * @param splAddonNm the splAddonNm to set
	 */
	public void setSplAddonNm(String splAddonNm) {
		this.splAddonNm = splAddonNm;
	}
	/**
	 * @return the splQuantity
	 */
	public float getSplQuantity() {
		return splQuantity;
	}
	/**
	 * @param splQuantity the splQuantity to set
	 */
	public void setSplQuantity(float splQuantity) {
		this.splQuantity = splQuantity;
	}
	/**
	 * @return the splCompName
	 */
	public String getSplCompName() {
		return splCompName;
	}
	/**
	 * @param splCompName the splCompName to set
	 */
	public void setSplCompName(String splCompName) {
		this.splCompName = splCompName;
	}
	/**
	 * @return the splCost
	 */
	public float getSplCost() {
		return splCost;
	}
	/**
	 * @param splCost the splCost to set
	 */
	public void setSplCost(float splCost) {
		this.splCost = splCost;
	}
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the splColNm
	 */
	public String getSplColNm() {
		return splColNm;
	}
	/**
	 * @param splColNm the splColNm to set
	 */
	public void setSplColNm(String splColNm) {
		this.splColNm = splColNm;
	}
	/**
	 * @return the addOnQuantity
	 */
	public float getAddOnQuantity() {
		return addOnQuantity;
	}
	/**
	 * @param addOnQuantity the addOnQuantity to set
	 */
	public void setAddOnQuantity(float addOnQuantity) {
		this.addOnQuantity = addOnQuantity;
	}
	/**
	 * @return the addOnColNm
	 */
	
	/**
	 * @return the addOnCost
	 */
	public float getAddOnCost() {
		return addOnCost;
	}
	/**
	 * @param addOnCost the addOnCost to set
	 */
	public void setAddOnCost(float addOnCost) {
		this.addOnCost = addOnCost;
	}
	
	/**
	 * @return the addInstrCompNm
	 */
	public String getAddInstrCompNm() {
		return addInstrCompNm;
	}
	/**
	 * @param addInstrCompNm the addInstrCompNm to set
	 */
	public void setAddInstrCompNm(String addInstrCompNm) {
		this.addInstrCompNm = addInstrCompNm;
	}
	/**
	 * @return the addOnCompNm
	 */
	public String getAddOnCompNm() {
		return addOnCompNm;
	}
	/**
	 * @param addOnCompNm the addOnCompNm to set
	 */
	public void setAddOnCompNm(String addOnCompNm) {
		this.addOnCompNm = addOnCompNm;
	}
	/**
	 * @return the itemApplInd
	 */
	public int getItemApplInd() {
		return itemApplInd;
	}
	/**
	 * @param itemApplInd the itemApplInd to set
	 */
	public void setItemApplInd(int itemApplInd) {
		this.itemApplInd = itemApplInd;
	}
	/**
	 * @return the itemOrder
	 */
	public int getItemOrder() {
		return itemOrder;
	}
	/**
	 * @param itemOrder the itemOrder to set
	 */
	public void setItemOrder(int itemOrder) {
		this.itemOrder = itemOrder;
	}
	/**
	 * @return the subItemCd
	 */
	public String getSubItemCd() {
		return subItemCd;
	}
	/**
	 * @param subItemCd the subItemCd to set
	 */
	public void setSubItemCd(String subItemCd) {
		this.subItemCd = subItemCd;
	}
	/**
	 * @return the subItemName
	 */
	public String getSubItemName() {
		return subItemName;
	}
	/**
	 * @param subItemName the subItemName to set
	 */
	public void setSubItemName(String subItemName) {
		this.subItemName = subItemName;
	}
	/**
	 * @return the subItemFlag
	 */
	public boolean isSubItemFlag() {
		return subItemFlag;
	}
	/**
	 * @param subItemFlag the subItemFlag to set
	 */
	public void setSubItemFlag(boolean subItemFlag) {
		this.subItemFlag = subItemFlag;
	}
	/**
	 * @return the subItemApplInd
	 */
	public int getSubItemApplInd() {
		return subItemApplInd;
	}
	/**
	 * @param subItemApplInd the subItemApplInd to set
	 */
	public void setSubItemApplInd(int subItemApplInd) {
		this.subItemApplInd = subItemApplInd;
	}
	/**
	 * @return the subItemOrder
	 */
	public int getSubItemOrder() {
		return subItemOrder;
	}
	/**
	 * @param subItemOrder the subItemOrder to set
	 */
	public void setSubItemOrder(int subItemOrder) {
		this.subItemOrder = subItemOrder;
	}
	/**
	 * @return the subItemTypeId
	 */
	public int getSubItemTypeId() {
		return subItemTypeId;
	}
	/**
	 * @param subItemTypeId the subItemTypeId to set
	 */
	public void setSubItemTypeId(int subItemTypeId) {
		this.subItemTypeId = subItemTypeId;
	}
	/**
	 * @return the subItemTypeCd
	 */
	public String getSubItemTypeCd() {
		return subItemTypeCd;
	}
	/**
	 * @param subItemTypeCd the subItemTypeCd to set
	 */
	public void setSubItemTypeCd(String subItemTypeCd) {
		this.subItemTypeCd = subItemTypeCd;
	}
	/**
	 * @return the subItemTypeName
	 */
	public String getSubItemTypeName() {
		return subItemTypeName;
	}
	/**
	 * @param subItemTypeName the subItemTypeName to set
	 */
	public void setSubItemTypeName(String subItemTypeName) {
		this.subItemTypeName = subItemTypeName;
	}
	/**
	 * @return the subItemTypeFlag
	 */
	public boolean isSubItemTypeFlag() {
		return subItemTypeFlag;
	}
	/**
	 * @param subItemTypeFlag the subItemTypeFlag to set
	 */
	public void setSubItemTypeFlag(boolean subItemTypeFlag) {
		this.subItemTypeFlag = subItemTypeFlag;
	}
	/**
	 * @return the subItemType
	 */
	public String getSubItemType() {
		return subItemType;
	}
	/**
	 * @param subItemType the subItemType to set
	 */
	public void setSubItemType(String subItemType) {
		this.subItemType = subItemType;
	}
//	/**
//	 * @return the techFlag
//	 */
//	public boolean isTechFlag() {
//		return techFlag;
//	}
//	/**
//	 * @param techFlag the techFlag to set
//	 */
//	public void setTechFlag(boolean techFlag) {
//		this.techFlag = techFlag;
//	}
	/**
	 * @return the dispInd
	 */
	public int getDispInd() {
		return dispInd;
	}
	/**
	 * @param dispInd the dispInd to set
	 */
	public void setDispInd(int dispInd) {
		this.dispInd = dispInd;
	}
//	/**
//	 * @return the comrFlag
//	 */
//	public boolean isComrFlag() {
//		return ComrFlag;
//	}
//	/**
//	 * @param comrFlag the comrFlag to set
//	 */
//	public void setComrFlag(boolean comrFlag) {
//		ComrFlag = comrFlag;
//	}
	/**
	 * @return the calcLinkFlag
	 */
	public boolean isCalcLinkFlag() {
		return CalcLinkFlag;
	}
	/**
	 * @param calcLinkFlag the calcLinkFlag to set
	 */
	public void setCalcLinkFlag(boolean calcLinkFlag) {
		CalcLinkFlag = calcLinkFlag;
	}
	/**
	 * @return the defaultFlag
	 */
	public boolean isDefaultFlag() {
		return DefaultFlag;
	}
	/**
	 * @param defaultFlag the defaultFlag to set
	 */
	public void setDefaultFlag(boolean defaultFlag) {
		DefaultFlag = defaultFlag;
	}
	/**
	 * @return the percentFlag
	 */
	public boolean isPercentFlag() {
		return PercentFlag;
	}
	/**
	 * @param percentFlag the percentFlag to set
	 */
	public void setPercentFlag(boolean percentFlag) {
		PercentFlag = percentFlag;
	}
	/**
	 * @return the dirPriceFlag
	 */
	public boolean isDirPriceFlag() {
		return DirPriceFlag;
	}
	/**
	 * @param dirPriceFlag the dirPriceFlag to set
	 */
	public void setDirPriceFlag(boolean dirPriceFlag) {
		DirPriceFlag = dirPriceFlag;
	}
	/**
	 * @return the percent
	 */
	public float getPercent() {
		return Percent;
	}
	/**
	 * @param percent the percent to set
	 */
	public void setPercent(float percent) {
		Percent = percent;
	}
	/**
	 * @return the dirPrice
	 */
	public float getDirPrice() {
		return dirPrice;
	}
	/**
	 * @param dirPrice the dirPrice to set
	 */
	public void setDirPrice(float dirPrice) {
		this.dirPrice = dirPrice;
	}
	/**
	 * @return the quotId
	 */
	public int getQuotId() {
		return quotId;
	}
	/**
	 * @param quotId the quotId to set
	 */
	public void setQuotId(int quotId) {
		this.quotId = quotId;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the itemNm
	 */
	public String getItemNm() {
		return itemNm;
	}
	/**
	 * @param itemNm the itemNm to set
	 */
	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}
	/**
	 * @return the subItemNm
	 */
	public String getSubItemNm() {
		return subItemNm;
	}
	/**
	 * @param subItemNm the subItemNm to set
	 */
	public void setSubItemNm(String subItemNm) {
		this.subItemNm = subItemNm;
	}
	/**
	 * @return the cost
	 */
	public float getCost() {
		return cost;
	}
	/**
	 * @param cost the cost to set
	 */
	public void setCost(float cost) {
		this.cost = cost;
	}
	public int getDetQuotId() {
		return detQuotId;
	}
	public void setDetQuotId(int detQuotId) {
		this.detQuotId = detQuotId;
	}
	/**
	 * @return the mastQuotId
	 */
	public int getMastQuotId() {
		return mastQuotId;
	}
	/**
	 * @param mastQuotId the mastQuotId to set
	 */
	public void setMastQuotId(int mastQuotId) {
		this.mastQuotId = mastQuotId;
	}
	/**
	 * @return the othColFlag
	 */
	public boolean isOthColFlag() {
		return OthColFlag;
	}
	/**
	 * @param othColFlag the othColFlag to set
	 */
	public void setOthColFlag(boolean othColFlag) {
		OthColFlag = othColFlag;
	}
	/**
	 * @return the othColNm
	 */
	public String getOthColNm() {
		return othColNm;
	}
	/**
	 * @param othColNm the othColNm to set
	 */
	public void setOthColNm(String othColNm) {
		this.othColNm = othColNm;
	}
	/**
	 * @return the itemCost
	 */
	public float getItemCost() {
		return itemCost;
	}
	/**
	 * @param itemCost the itemCost to set
	 */
	public void setItemCost(float itemCost) {
		this.itemCost = itemCost;
	}
//	/**
//	 * @return the othColValFlag
//	 */
//	public boolean isOthColValFlag() {
//		return othColValFlag;
//	}
//	/**
//	 * @param othColValFlag the othColValFlag to set
//	 */
//	public void setOthColValFlag(boolean othColValFlag) {
//		this.othColValFlag = othColValFlag;
//	}
	/**
	 * @return the rhsCost
	 */
	public float getRhsCost() {
		return rhsCost;
	}
	/**
	 * @param rhsCost the rhsCost to set
	 */
	public void setRhsCost(float rhsCost) {
		this.rhsCost = rhsCost;
	}
	/**
	 * @return the rhsColQuantity
	 */
	public float getRhsColQuantity() {
		return rhsColQuantity;
	}
	/**
	 * @param rhsColQuantity the rhsColQuantity to set
	 */
	public void setRhsColQuantity(float rhsColQuantity) {
		this.rhsColQuantity = rhsColQuantity;
	}
	
	/**
	 * @return the rhsCostMeFlag
	 */
	/**
	 * @return the addOnCostMeFlag
	 */
//	public boolean isAddOnCostMeFlag() {
//		return addOnCostMeFlag;
//	}
//	/**
//	 * @param addOnCostMeFlag the addOnCostMeFlag to set
//	 */
//	public void setAddOnCostMeFlag(boolean addOnCostMeFlag) {
//		this.addOnCostMeFlag = addOnCostMeFlag;
//	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the categoryValCode
	 */
	public String getCategoryValCode() {
		return categoryValCode;
	}
	/**
	 * @param categoryValCode the categoryValCode to set
	 */
	public void setCategoryValCode(String categoryValCode) {
		this.categoryValCode = categoryValCode;
	}
	/**
	 * @return the subItemCode
	 */
	public String getSubItemCode() {
		return subItemCode;
	}
	/**
	 * @param subItemCode the subItemCode to set
	 */
	public void setSubItemCode(String subItemCode) {
		this.subItemCode = subItemCode;
	}
	/**
	 * @return the groupCode
	 */
	public String getGroupCode() {
		return groupCode;
	}
	/**
	 * @param groupCode the groupCode to set
	 */
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	/**
	 * @return the quotGenId
	 */
	public int getQuotGenId() {
		return quotGenId;
	}
	/**
	 * @param quotGenId the quotGenId to set
	 */
	public void setQuotGenId(int quotGenId) {
		this.quotGenId = quotGenId;
	}
	/**
	 * @return the isEnabled
	 */
	public boolean isEnabled() {
		return isEnabled;
	}
	/**
	 * @param isEnabled the isEnabled to set
	 */
	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	/**
	 * @return the colValComments
	 */
	public String getColValComments() {
		return colValComments;
	}
	/**
	 * @param colValComments the colValComments to set
	 */
	public void setColValComments(String colValComments) {
		this.colValComments = colValComments;
	}
	/**
	 * @return the techRemarks
	 */
	public String getTechRemarks() {
		return techRemarks;
	}
	/**
	 * @param techRemarks the techRemarks to set
	 */
	public void setTechRemarks(String techRemarks) {
		this.techRemarks = techRemarks;
	}
	/**
	 * @return the comrRemarks
	 */
	public String getComrRemarks() {
		return comrRemarks;
	}
	/**
	 * @param comrRemarks the comrRemarks to set
	 */
	public void setComrRemarks(String comrRemarks) {
		this.comrRemarks = comrRemarks;
	}
	/**
	 * @return the techComments
	 */
	public String getTechComments() {
		return techComments;
	}
	/**
	 * @param techComments the techComments to set
	 */
	public void setTechComments(String techComments) {
		this.techComments = techComments;
	}
	/**
	 * @return the comrComments
	 */
	public String getComrComments() {
		return comrComments;
	}
	/**
	 * @param comrComments the comrComments to set
	 */
	public void setComrComments(String comrComments) {
		this.comrComments = comrComments;
	}
	/**
	 * @return the lhsFlag
	 */
	
	/**
	 * @return the rhsFlag
	 */
	public boolean isRhsFlag() {
		return rhsFlag;
	}
	/**
	 * @param rhsFlag the rhsFlag to set
	 */
	public void setRhsFlag(boolean rhsFlag) {
		this.rhsFlag = rhsFlag;
	}
	/**
	 * @return the othersCost
	 */
	public float getOthersCost() {
		return othersCost;
	}
	/**
	 * @param othersCost the othersCost to set
	 */
	public void setOthersCost(float othersCost) {
		this.othersCost = othersCost;
	}
	/**
	 * @return the categoryValName
	 */
	public String getCategoryValName() {
		return categoryValName;
	}
	/**
	 * @param categoryValName the categoryValName to set
	 */
	public void setCategoryValName(String categoryValName) {
		this.categoryValName = categoryValName;
	}
	/**
	 * @return the othColValFlag
	 */
	public int getOthColValFlag() {
		return othColValFlag;
	}
	/**
	 * @param othColValFlag the othColValFlag to set
	 */
	public void setOthColValFlag(int othColValFlag) {
		this.othColValFlag = othColValFlag;
	}
	/**
	 * @return the techFlag
	 */
	public int getTechFlag() {
		return techFlag;
	}
	/**
	 * @param techFlag the techFlag to set
	 */
	public void setTechFlag(int techFlag) {
		this.techFlag = techFlag;
	}
	/**
	 * @return the comrFlag
	 */
	public int getComrFlag() {
		return ComrFlag;
	}
	/**
	 * @param comrFlag the comrFlag to set
	 */
	public void setComrFlag(int comrFlag) {
		ComrFlag = comrFlag;
	}
	/**
	 * @return the addOnCostMeFlag
	 */
	public int getAddOnCostMeFlag() {
		return addOnCostMeFlag;
	}
	/**
	 * @param addOnCostMeFlag the addOnCostMeFlag to set
	 */
	public void setAddOnCostMeFlag(int addOnCostMeFlag) {
		this.addOnCostMeFlag = addOnCostMeFlag;
	}
	/**
	 * @return the addOnOverwrtirttenFlag1
	 */
	public int getAddOnOverwrittenFlag1() {
		return addOnOverwrittenFlag1;
	}
	/**
	 * @param addOnOverwrtirttenFlag1 the addOnOverwrtirttenFlag1 to set
	 */
	public void setAddOnOverwrtirttenFlag1(int addOnOverwrtirttenFlag1) {
		this.addOnOverwrittenFlag1 = addOnOverwrtirttenFlag1;
	}
	/**
	 * @return the f2fItemId
	 */
	public int getF2fItemId() {
		return f2fItemId;
	}
	/**
	 * @param f2fItemId the f2fItemId to set
	 */
	public void setF2fItemId(int f2fItemId) {
		this.f2fItemId = f2fItemId;
	}
	/**
	 * @return the rhsColcomments
	 */
	public String getRhsColcomments() {
		return RhsColcomments;
	}
	/**
	 * @param rhsColcomments the rhsColcomments to set
	 */
	public void setRhsColcomments(String rhsColcomments) {
		RhsColcomments = rhsColcomments;
	}
	/**
	 * @param addOnOverwrittenFlag1 the addOnOverwrittenFlag1 to set
	 */
	public void setAddOnOverwrittenFlag1(int addOnOverwrittenFlag1) {
		this.addOnOverwrittenFlag1 = addOnOverwrittenFlag1;
	}
	/**
	 * @return the rhsColTechcomments
	 */
	public String getRhsColTechcomments() {
		return rhsColTechcomments;
	}
	/**
	 * @param rhsColTechcomments the rhsColTechcomments to set
	 */
	public void setRhsColTechcomments(String rhsColTechcomments) {
		this.rhsColTechcomments = rhsColTechcomments;
	}
	/**
	 * @return the rhsColComrcomments
	 */
	public String getRhsColComrcomments() {
		return rhsColComrcomments;
	}
	/**
	 * @param rhsColComrcomments the rhsColComrcomments to set
	 */
	public void setRhsColComrcomments(String rhsColComrcomments) {
		this.rhsColComrcomments = rhsColComrcomments;
	}
	/**
	 * @return the addOnFlag
	 */
	public boolean isAddOnFlag() {
		return addOnFlag;
	}
	/**
	 * @param addOnFlag the addOnFlag to set
	 */
	public void setAddOnFlag(boolean addOnFlag) {
		this.addOnFlag = addOnFlag;
	}
	/**
	 * @return the subItemQuantity
	 */
	public float getSubItemQuantity() {
		return subItemQuantity;
	}
	/**
	 * @param subItemQuantity the subItemQuantity to set
	 */
	public void setSubItemQuantity(float subItemQuantity) {
		this.subItemQuantity = subItemQuantity;
	}
	/**
	 * @return the subItemCost
	 */
	public float getSubItemCost() {
		return subItemCost;
	}
	/**
	 * @param subItemCost the subItemCost to set
	 */
	public void setSubItemCost(float subItemCost) {
		this.subItemCost = subItemCost;
	}
	/**
	 * @return the subItemTechRemarks
	 */
	public String getSubItemTechRemarks() {
		return subItemTechRemarks;
	}
	/**
	 * @param subItemTechRemarks the subItemTechRemarks to set
	 */
	public void setSubItemTechRemarks(String subItemTechRemarks) {
		this.subItemTechRemarks = subItemTechRemarks;
	}
	/**
	 * @return the subItemComrRemarks
	 */
	public String getSubItemComrRemarks() {
		return subItemComrRemarks;
	}
	/**
	 * @param subItemComrRemarks the subItemComrRemarks to set
	 */
	public void setSubItemComrRemarks(String subItemComrRemarks) {
		this.subItemComrRemarks = subItemComrRemarks;
	}
	/**
	 * @return the subItemTypeQuantity
	 */
	public float getSubItemTypeQuantity() {
		return subItemTypeQuantity;
	}
	/**
	 * @param subItemTypeQuantity the subItemTypeQuantity to set
	 */
	public void setSubItemTypeQuantity(float subItemTypeQuantity) {
		this.subItemTypeQuantity = subItemTypeQuantity;
	}
	/**
	 * @return the subItemTypeCost
	 */
	public float getSubItemTypeCost() {
		return subItemTypeCost;
	}
	/**
	 * @param subItemTypeCost the subItemTypeCost to set
	 */
	public void setSubItemTypeCost(float subItemTypeCost) {
		this.subItemTypeCost = subItemTypeCost;
	}
	/**
	 * @return the subItemTypeTechRemarks
	 */
	public String getSubItemTypeTechRemarks() {
		return subItemTypeTechRemarks;
	}
	/**
	 * @param subItemTypeTechRemarks the subItemTypeTechRemarks to set
	 */
	public void setSubItemTypeTechRemarks(String subItemTypeTechRemarks) {
		this.subItemTypeTechRemarks = subItemTypeTechRemarks;
	}
	/**
	 * @return the subItemTypeComrRemarks
	 */
	public String getSubItemTypeComrRemarks() {
		return subItemTypeComrRemarks;
	}
	/**
	 * @param subItemTypeComrRemarks the subItemTypeComrRemarks to set
	 */
	public void setSubItemTypeComrRemarks(String subItemTypeComrRemarks) {
		this.subItemTypeComrRemarks = subItemTypeComrRemarks;
	}
	/**
	 * @return the mechItemId
	 */
	public int getMechItemId() {
		return mechItemId;
	}
	/**
	 * @param mechItemId the mechItemId to set
	 */
	public void setMechItemId(int mechItemId) {
		this.mechItemId = mechItemId;
	}
	/**
	 * @return the inputCost
	 */
	public float getInputCost() {
		return inputCost;
	}
	/**
	 * @param inputCost the inputCost to set
	 */
	public void setInputCost(float inputCost) {
		this.inputCost = inputCost;
	}
	/**
	 * @return the f2FItem
	 */
	public String getF2fItem() {
		return f2fItem;
	}
	/**
	 * @param f2fItem the f2FItem to set
	 */
	public void setF2fItem(String f2fItem) {
		f2fItem = f2fItem;
	}
	/**
	 * @return the addOnFlg
	 */
	public int getAddOnFlg() {
		return addOnFlg;
	}
	/**
	 * @param addOnFlg the addOnFlg to set
	 */
	public void setAddOnFlg(int addOnFlg) {
		this.addOnFlg = addOnFlg;
	}
	/**
	 * @return the basicCost
	 */
	public float getBasicCost() {
		return basicCost;
	}
	/**
	 * @param basicCost the basicCost to set
	 */
	public void setBasicCost(float basicCost) {
		this.basicCost = basicCost;
	}
	/**
	 * @return the addOnNewColFlag
	 */
	
	private int otherItemInstrFlag;
	public int getOtherItemInstrFlag() {
		return otherItemInstrFlag;
	}
	public void setOtherItemInstrFlag(int otherItemInstrFlag) {
		this.otherItemInstrFlag = otherItemInstrFlag;
	}
	public int getAddOnNewColFlag() {
		return addOnNewColFlag;
	}
	/**
	 * @param addOnNewColFlag the addOnNewColFlag to set
	 */
	public void setAddOnNewColFlag(int addOnNewColFlag) {
		this.addOnNewColFlag = addOnNewColFlag;
	}
	/**
	 * @return the colType
	 */
	public String getColType() {
		return colType;
	}
	/**
	 * @param colType the colType to set
	 */
	public void setColType(String colType) {
		this.colType = colType;
	}
	/**
	 * @return the extScopeRhsCost
	 */
	public float getExtScopeRhsCost() {
		return extScopeRhsCost;
	}
	/**
	 * @param extScopeRhsCost the extScopeRhsCost to set
	 */
	public void setExtScopeRhsCost(float extScopeRhsCost) {
		this.extScopeRhsCost = extScopeRhsCost;
	}
	/**
	 * @return the extScopeCost
	 */
	public float getExtScopeCost() {
		return extScopeCost;
	}
	/**
	 * @param extScopeCost the extScopeCost to set
	 */
	public void setExtScopeCost(float extScopeCost) {
		this.extScopeCost = extScopeCost;
	}
	/**
	 * @return the totalExtCost
	 */
	public float getTotalExtCost() {
		return totalExtCost;
	}
	/**
	 * @param totalExtCost the totalExtCost to set
	 */
	public void setTotalExtCost(float totalExtCost) {
		this.totalExtCost = totalExtCost;
	}
	/**
	 * @return the newPanelFlag
	 */
	public boolean isNewPanelFlag() {
		return newPanelFlag;
	}
	/**
	 * @param newPanelFlag the newPanelFlag to set
	 */
	public void setNewPanelFlag(boolean newPanelFlag) {
		this.newPanelFlag = newPanelFlag;
	}
	/**
	 * @return the genType
	 */
	public String getGenType() {
		return genType;
	}
	/**
	 * @param genType the genType to set
	 */
	public void setGenType(String genType) {
		this.genType = genType;
	}
	/**
	 * @return the genInNo
	 */
	public int getGenInNo() {
		return genInNo;
	}
	/**
	 * @param genInNo the genInNo to set
	 */
	public void setGenInNo(int genInNo) {
		this.genInNo = genInNo;
	}
	/**
	 * @return the genInNm
	 */
	public String getGenInNm() {
		return genInNm;
	}
	/**
	 * @param genInNm the genInNm to set
	 */
	public void setGenInNm(String genInNm) {
		this.genInNm = genInNm;
	}
	
	/**
	 * @return the genInId
	 */
	public int getGenInId() {
		return genInId;
	}
	/**
	 * @param genInId the genInId to set
	 */
	public void setGenInId(int genInId) {
		this.genInId = genInId;
	}
	/**
	 * @return the eleFilterId
	 */
	public int getEleFilterId() {
		return eleFilterId;
	}
	/**
	 * @param eleFilterId the eleFilterId to set
	 */
	public void setEleFilterId(int eleFilterId) {
		this.eleFilterId = eleFilterId;
	}
	/**
	 * @return the eleType
	 */
	public String getEleType() {
		return eleType;
	}
	/**
	 * @param eleType the eleType to set
	 */
	public void setEleType(String eleType) {
		this.eleType = eleType;
	}
	/**
	 * @return the filterId
	 */
	public int getFilterId() {
		return filterId;
	}
	/**
	 * @param filterId the filterId to set
	 */
	public void setFilterId(int filterId) {
		this.filterId = filterId;
	}
	
	public String filterNm;
	public String getFilterNm() {
		return filterNm;
	}
	public void setFilterNm(String filterNm) {
		this.filterNm = filterNm;
	}
	/**
	 * @return the quotFilterId
	 */
	public int getQuotFilterId() {
		return quotFilterId;
	}
	/**
	 * @param quotFilterId the quotFilterId to set
	 */
	public void setQuotFilterId(int quotFilterId) {
		this.quotFilterId = quotFilterId;
	}
	/**
	 * @return the eleItemId
	 */
	public int getEleItemId() {
		return eleItemId;
	}
	/**
	 * @param eleItemId the eleItemId to set
	 */
	public void setEleItemId(int eleItemId) {
		this.eleItemId = eleItemId;
	}
	/**
	 * @return the linkFlag
	 */
	public boolean isLinkFlag() {
		return linkFlag;
	}
	/**
	 * @param linkFlag the linkFlag to set
	 */
	public void setLinkFlag(boolean linkFlag) {
		this.linkFlag = linkFlag;
	}
	/**
	 * @return the dependFlag
	 */
	public boolean isDependFlag() {
		return dependFlag;
	}
	/**
	 * @param dependFlag the dependFlag to set
	 */
	public void setDependFlag(boolean dependFlag) {
		this.dependFlag = dependFlag;
	}
	/**
	 * @return the f2fAddOnFlag
	 */
	public boolean isF2fAddOnFlag() {
		return f2fAddOnFlag;
	}
	/**
	 * @param f2fAddOnFlag the f2fAddOnFlag to set
	 */
	public void setF2fAddOnFlag(boolean f2fAddOnFlag) {
		this.f2fAddOnFlag = f2fAddOnFlag;
	}
	/**
	 * @return the desSubItemId
	 */
	public int getDesSubItemId() {
		return desSubItemId;
	}
	/**
	 * @param desSubItemId the desSubItemId to set
	 */
	public void setDesSubItemId(int desSubItemId) {
		this.desSubItemId = desSubItemId;
	}
	/**
	 * @return the subColValFlag
	 */
	public boolean isSubColValFlag() {
		return subColValFlag;
	}
	/**
	 * @param subColValFlag the subColValFlag to set
	 */
	public void setSubColValFlag(boolean subColValFlag) {
		this.subColValFlag = subColValFlag;
	}
	/**
	 * @return the subColValName
	 */
	public String getSubColValName() {
		return subColValName;
	}
	/**
	 * @param subColValName the subColValName to set
	 */
	public void setSubColValName(String subColValName) {
		this.subColValName = subColValName;
	}
	/**
	 * @return the make
	 */
	public String getMake() {
		return make;
	}
	/**
	 * @param make the make to set
	 */
	public void setMake(String make) {
		this.make = make;
	}
	/**
	 * @return the disableColValCd
	 */
	public String getDisableColValCd() {
		return disableColValCd;
	}
	/**
	 * @param disableColValCd the disableColValCd to set
	 */
	public void setDisableColValCd(String disableColValCd) {
		this.disableColValCd = disableColValCd;
	}
	/**
	 * @return the subColValCode
	 */
	public String getSubColValCode() {
		return subColValCode;
	}
	/**
	 * @param subColValCode the subColValCode to set
	 */
	public void setSubColValCode(String subColValCode) {
		this.subColValCode = subColValCode;
	}
	/**
	 * @return the addOnCostPer
	 */
	public float getAddOnCostPer() {
		return addOnCostPer;
	}
	/**
	 * @param addOnCostPer the addOnCostPer to set
	 */
	public void setAddOnCostPer(float addOnCostPer) {
		this.addOnCostPer = addOnCostPer;
	}
	/**
	 * @return the addOnDirCost
	 */
	public float getAddOnDirCost() {
		return addOnDirCost;
	}
	/**
	 * @param addOnDirCost the addOnDirCost to set
	 */
	public void setAddOnDirCost(float addOnDirCost) {
		this.addOnDirCost = addOnDirCost;
	}
	
	/**
	 * @return the mechAuxTotalPrice
	 */
	public float getMechAuxTotalPrice() {
		return mechAuxTotalPrice;
	}
	/**
	 * @param mechAuxTotalPrice the mechAuxTotalPrice to set
	 */
	public void setMechAuxTotalPrice(float mechAuxTotalPrice) {
		this.mechAuxTotalPrice = mechAuxTotalPrice;
	}
	/**
	 * @return the desSubItemName
	 */
	public String getDesSubItemName() {
		return desSubItemName;
	}
	/**
	 * @param desSubItemName the desSubItemName to set
	 */
	public void setDesSubItemName(String desSubItemName) {
		this.desSubItemName = desSubItemName;
	}
	/**
	 * @return the colDispInd
	 */
	public int getColDispInd() {
		return colDispInd;
	}
	/**
	 * @param colDispInd the colDispInd to set
	 */
	public void setColDispInd(int colDispInd) {
		this.colDispInd = colDispInd;
	}
	/**
	 * @return the colOrderIn
	 */
	public int getColOrderIn() {
		return colOrderIn;
	}
	/**
	 * @param colOrderIn the colOrderIn to set
	 */
	public void setColOrderIn(int colOrderIn) {
		this.colOrderIn = colOrderIn;
	}
	/**
	 * @return the subColValNm
	 */
	public String getSubColValNm() {
		return subColValNm;
	}
	/**
	 * @param subColValNm the subColValNm to set
	 */
	public void setSubColValNm(String subColValNm) {
		this.subColValNm = subColValNm;
	}
	/**
	 * @return the scopeCode
	 */
	public String getScopeCode() {
		return scopeCode;
	}
	/**
	 * @param scopeCode the scopeCode to set
	 */
	public void setScopeCode(String scopeCode) {
		this.scopeCode = scopeCode;
	}
	/**
	 * @return the dboMechItem
	 */
	public String getDboMechItem() {
		return dboMechItem;
	}
	/**
	 * @param dboMechItem the dboMechItem to set
	 */
	public void setDboMechItem(String dboMechItem) {
		this.dboMechItem = dboMechItem;
	}
	/**
	 * @return the pkgQuotId
	 */
	public int getPkgQuotId() {
		return pkgQuotId;
	}
	/**
	 * @param pkgQuotId the pkgQuotId to set
	 */
	public void setPkgQuotId(int pkgQuotId) {
		this.pkgQuotId = pkgQuotId;
	}
	/**
	 * @return the packageTypeId
	 */
	public int getPackageTypeId() {
		return packageTypeId;
	}
	/**
	 * @param packageTypeId the packageTypeId to set
	 */
	public void setPackageTypeId(int packageTypeId) {
		this.packageTypeId = packageTypeId;
	}
	/**
	 * @return the packageTypeName
	 */
	public String getPackageTypeName() {
		return packageTypeName;
	}
	/**
	 * @param packageTypeName the packageTypeName to set
	 */
	public void setPackageTypeName(String packageTypeName) {
		this.packageTypeName = packageTypeName;
	}
	/**
	 * @return the packageTypeCode
	 */
	public String getPackageTypeCode() {
		return packageTypeCode;
	}
	/**
	 * @param packageTypeCode the packageTypeCode to set
	 */
	public void setPackageTypeCode(String packageTypeCode) {
		this.packageTypeCode = packageTypeCode;
	}
	/**
	 * @return the framePowerId
	 */
	public int getFramePowerId() {
		return framePowerId;
	}
	/**
	 * @param framePowerId the framePowerId to set
	 */
	public void setFramePowerId(int framePowerId) {
		this.framePowerId = framePowerId;
	}
	/**
	 * @return the frameName
	 */
	public String getFrameName() {
		return frameName;
	}
	/**
	 * @param frameName the frameName to set
	 */
	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}
	/**
	 * @return the condensingTypeId
	 */
	public int getCondensingTypeId() {
		return condensingTypeId;
	}
	/**
	 * @param condensingTypeId the condensingTypeId to set
	 */
	public void setCondensingTypeId(int condensingTypeId) {
		this.condensingTypeId = condensingTypeId;
	}
	/**
	 * @return the condensingTypeName
	 */
	public String getCondensingTypeName() {
		return condensingTypeName;
	}
	/**
	 * @param condensingTypeName the condensingTypeName to set
	 */
	public void setCondensingTypeName(String condensingTypeName) {
		this.condensingTypeName = condensingTypeName;
	}
	/**
	 * @return the frameDesc
	 */
	public String getFrameDesc() {
		return frameDesc;
	}
	/**
	 * @param frameDesc the frameDesc to set
	 */
	public void setFrameDesc(String frameDesc) {
		this.frameDesc = frameDesc;
	}
	/**
	 * @return the condensingType
	 */
	public String getCondensingType() {
		return condensingType;
	}
	/**
	 * @param condensingType the condensingType to set
	 */
	public void setCondensingType(String condensingType) {
		this.condensingType = condensingType;
	}
	/**
	 * @return the typeOfChargeId
	 */
	public int getTypeOfChargeId() {
		return typeOfChargeId;
	}
	/**
	 * @param typeOfChargeId the typeOfChargeId to set
	 */
	public void setTypeOfChargeId(int typeOfChargeId) {
		this.typeOfChargeId = typeOfChargeId;
	}
	/**
	 * @return the typeOfCharge
	 */
	public String getTypeOfCharge() {
		return typeOfCharge;
	}
	/**
	 * @param typeOfCharge the typeOfCharge to set
	 */
	public void setTypeOfCharge(String typeOfCharge) {
		this.typeOfCharge = typeOfCharge;
	}
	/**
	 * @return the typeOfChargeCd
	 */
	public String getTypeOfChargeCd() {
		return typeOfChargeCd;
	}
	/**
	 * @param typeOfChargeCd the typeOfChargeCd to set
	 */
	public void setTypeOfChargeCd(String typeOfChargeCd) {
		this.typeOfChargeCd = typeOfChargeCd;
	}
	/**
	 * @return the loadingId
	 */
	public int getLoadingId() {
		return loadingId;
	}
	/**
	 * @param loadingId the loadingId to set
	 */
	public void setLoadingId(int loadingId) {
		this.loadingId = loadingId;
	}
	/**
	 * @return the loadingName
	 */
	public String getLoadingName() {
		return loadingName;
	}
	/**
	 * @param loadingName the loadingName to set
	 */
	public void setLoadingName(String loadingName) {
		this.loadingName = loadingName;
	}
	/**
	 * @return the loadingCd
	 */
	public String getLoadingCd() {
		return loadingCd;
	}
	/**
	 * @param loadingCd the loadingCd to set
	 */
	public void setLoadingCd(String loadingCd) {
		this.loadingCd = loadingCd;
	}
	/**
	 * @return the lodgingId
	 */
	public int getLodgingId() {
		return lodgingId;
	}
	/**
	 * @param lodgingId the lodgingId to set
	 */
	public void setLodgingId(int lodgingId) {
		this.lodgingId = lodgingId;
	}
	/**
	 * @return the lodgingName
	 */
	public String getLodgingName() {
		return lodgingName;
	}
	/**
	 * @param lodgingName the lodgingName to set
	 */
	public void setLodgingName(String lodgingName) {
		this.lodgingName = lodgingName;
	}
	/**
	 * @return the lodgingCd
	 */
	public String getLodgingCd() {
		return lodgingCd;
	}
	/**
	 * @param lodgingCd the lodgingCd to set
	 */
	public void setLodgingCd(String lodgingCd) {
		this.lodgingCd = lodgingCd;
	}
	/**
	 * @return the noOfManDays
	 */
	public int getNoOfManDays() {
		return noOfManDays;
	}
	/**
	 * @param noOfManDays the noOfManDays to set
	 */
	public void setNoOfManDays(int noOfManDays) {
		this.noOfManDays = noOfManDays;
	}
	/**
	 * @return the transMastId
	 */
	public int getTransMastId() {
		return transMastId;
	}
	/**
	 * @param transMastId the transMastId to set
	 */
	public void setTransMastId(int transMastId) {
		this.transMastId = transMastId;
	}
	/**
	 * @return the transTypeId
	 */
	public int getTransTypeId() {
		return transTypeId;
	}
	/**
	 * @param transTypeId the transTypeId to set
	 */
	public void setTransTypeId(int transTypeId) {
		this.transTypeId = transTypeId;
	}
	/**
	 * @return the transType
	 */
	public String getTransType() {
		return transType;
	}
	/**
	 * @param transType the transType to set
	 */
	public void setTransType(String transType) {
		this.transType = transType;
	}
	/**
	 * @return the fOBPlace
	 */
	public String getfOBPlace() {
		return fOBPlace;
	}
	/**
	 * @param fOBPlace the fOBPlace to set
	 */
	public void setfOBPlace(String fOBPlace) {
		this.fOBPlace = fOBPlace;
	}
	/**
	 * @return the compoId
	 */
	public int getCompoId() {
		return compoId;
	}
	/**
	 * @param compoId the compoId to set
	 */
	public void setCompoId(int compoId) {
		this.compoId = compoId;
	}
	/**
	 * @return the compoName
	 */
	public String getCompoName() {
		return compoName;
	}
	/**
	 * @param compoName the compoName to set
	 */
	public void setCompoName(String compoName) {
		this.compoName = compoName;
	}
	/**
	 * @return the numberOfVehicle
	 */
	public int getNumberOfVehicle() {
		return numberOfVehicle;
	}
	/**
	 * @param numberOfVehicle the numberOfVehicle to set
	 */
	public void setNumberOfVehicle(int numberOfVehicle) {
		this.numberOfVehicle = numberOfVehicle;
	}
	/**
	 * @return the toPlace
	 */
	public String getToPlace() {
		return toPlace;
	}
	/**
	 * @param toPlace the toPlace to set
	 */
	public void setToPlace(String toPlace) {
		this.toPlace = toPlace;
	}
	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}
	/**
	 * @return the portId
	 */
	public int getPortId() {
		return portId;
	}
	/**
	 * @param portId the portId to set
	 */
	public void setPortId(int portId) {
		this.portId = portId;
	}
	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}
	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	/**
	 * @return the portName
	 */
	public String getPortName() {
		return portName;
	}
	/**
	 * @param portName the portName to set
	 */
	public void setPortName(String portName) {
		this.portName = portName;
	}
	/**
	 * @return the compPrice
	 */
	public float getCompPrice() {
		return compPrice;
	}
	/**
	 * @param compPrice the compPrice to set
	 */
	public void setCompPrice(float compPrice) {
		this.compPrice = compPrice;
	}
	/**
	 * @return the fobPrice
	 */
	public float getFobPrice() {
		return fobPrice;
	}
	/**
	 * @param fobPrice the fobPrice to set
	 */
	public void setFobPrice(float fobPrice) {
		this.fobPrice = fobPrice;
	}
	/**
	 * @return the otherItemId
	 */
	public int getOtherItemId() {
		return otherItemId;
	}
	/**
	 * @param otherItemId the otherItemId to set
	 */
	public void setOtherItemId(int otherItemId) {
		this.otherItemId = otherItemId;
	}
	/**
	 * @return the insurance
	 */
	public float getInsurance() {
		return insurance;
	}
	/**
	 * @param insurance the insurance to set
	 */
	public void setInsurance(float insurance) {
		this.insurance = insurance;
	}
	/**
	 * @return the inTravelExpensesReq
	 */
	public int getInTravelExpensesReq() {
		return inTravelExpensesReq;
	}
	/**
	 * @param inTravelExpensesReq the inTravelExpensesReq to set
	 */
	public void setInTravelExpensesReq(int inTravelExpensesReq) {
		this.inTravelExpensesReq = inTravelExpensesReq;
	}
	/**
	 * @return the inNoOfVisit
	 */
	public int getInNoOfVisit() {
		return inNoOfVisit;
	}
	/**
	 * @param inNoOfVisit the inNoOfVisit to set
	 */
	public void setInNoOfVisit(int inNoOfVisit) {
		this.inNoOfVisit = inNoOfVisit;
	}
	/**
	 * @return the inCostForEachVisit
	 */
	public int getInCostForEachVisit() {
		return inCostForEachVisit;
	}
	/**
	 * @param inCostForEachVisit the inCostForEachVisit to set
	 */
	public void setInCostForEachVisit(int inCostForEachVisit) {
		this.inCostForEachVisit = inCostForEachVisit;
	}
	/**
	 * @return the splProvision
	 */
	public float getSplProvision() {
		return splProvision;
	}
	/**
	 * @param splProvision the splProvision to set
	 */
	public void setSplProvision(float splProvision) {
		this.splProvision = splProvision;
	}
	/**
	 * @return the travelExpenses
	 */
	public float getTravelExpenses() {
		return travelExpenses;
	}
	/**
	 * @param travelExpenses the travelExpenses to set
	 */
	public void setTravelExpenses(float travelExpenses) {
		this.travelExpenses = travelExpenses;
	}
	/**
	 * @return the turbineContigency
	 */
	public float getTurbineContigency() {
		return turbineContigency;
	}
	/**
	 * @param turbineContigency the turbineContigency to set
	 */
	public void setTurbineContigency(float turbineContigency) {
		this.turbineContigency = turbineContigency;
	}
	/**
	 * @return the dboContigency
	 */
	public float getDboContigency() {
		return dboContigency;
	}
	/**
	 * @param dboContigency the dboContigency to set
	 */
	public void setDboContigency(float dboContigency) {
		this.dboContigency = dboContigency;
	}
	/**
	 * @return the inpAgencyCommission
	 */
	public float getInpAgencyCommission() {
		return inpAgencyCommission;
	}
	/**
	 * @param inpAgencyCommission the inpAgencyCommission to set
	 */
	public void setInpAgencyCommission(float inpAgencyCommission) {
		this.inpAgencyCommission = inpAgencyCommission;
	}
	/**
	 * @return the salesExpenses
	 */
	public float getSalesExpenses() {
		return salesExpenses;
	}
	/**
	 * @param salesExpenses the salesExpenses to set
	 */
	public void setSalesExpenses(float salesExpenses) {
		this.salesExpenses = salesExpenses;
	}
	/**
	 * @return the engineCharges
	 */
	public float getEngineCharges() {
		return engineCharges;
	}
	/**
	 * @param engineCharges the engineCharges to set
	 */
	public void setEngineCharges(float engineCharges) {
		this.engineCharges = engineCharges;
	}
	/**
	 * @return the intrestNoOfMonths
	 */
	public float getIntrestNoOfMonths() {
		return intrestNoOfMonths;
	}
	/**
	 * @param intrestNoOfMonths the intrestNoOfMonths to set
	 */
	public void setIntrestNoOfMonths(float intrestNoOfMonths) {
		this.intrestNoOfMonths = intrestNoOfMonths;
	}
	/**
	 * @return the others
	 */
	public float getOthers() {
		return others;
	}
	/**
	 * @param others the others to set
	 */
	public void setOthers(float others) {
		this.others = others;
	}
	/**
	 * @return the othersRemarks
	 */
	public String getOthersRemarks() {
		return othersRemarks;
	}
	/**
	 * @param othersRemarks the othersRemarks to set
	 */
	public void setOthersRemarks(String othersRemarks) {
		this.othersRemarks = othersRemarks;
	}
	/**
	 * @return the warrantyPeriod
	 */
	public float getWarrantyPeriod() {
		return warrantyPeriod;
	}
	/**
	 * @param warrantyPeriod the warrantyPeriod to set
	 */
	public void setWarrantyPeriod(float warrantyPeriod) {
		this.warrantyPeriod = warrantyPeriod;
	}
	/**
	 * @return the varProfit
	 */
	public float getVarProfit() {
		return varProfit;
	}
	/**
	 * @param varProfit the varProfit to set
	 */
	public void setVarProfit(float varProfit) {
		this.varProfit = varProfit;
	}
	/**
	 * @return the inpLdforLateDelivery
	 */
	public float getInpLdforLateDelivery() {
		return inpLdforLateDelivery;
	}
	/**
	 * @param inpLdforLateDelivery the inpLdforLateDelivery to set
	 */
	public void setInpLdforLateDelivery(float inpLdforLateDelivery) {
		this.inpLdforLateDelivery = inpLdforLateDelivery;
	}
	/**
	 * @return the inpLdPerformance
	 */
	public float getInpLdPerformance() {
		return inpLdPerformance;
	}
	/**
	 * @param inpLdPerformance the inpLdPerformance to set
	 */
	public void setInpLdPerformance(float inpLdPerformance) {
		this.inpLdPerformance = inpLdPerformance;
	}
	/**
	 * @return the intrestPercentage
	 */
	public float getIntrestPercentage() {
		return intrestPercentage;
	}
	/**
	 * @param intrestPercentage the intrestPercentage to set
	 */
	public void setIntrestPercentage(float intrestPercentage) {
		this.intrestPercentage = intrestPercentage;
	}
	/**
	 * @return the inpBG1
	 */
	public float getInpBG1() {
		return inpBG1;
	}
	/**
	 * @param inpBG1 the inpBG1 to set
	 */
	public void setInpBG1(float inpBG1) {
		this.inpBG1 = inpBG1;
	}
	/**
	 * @return the inpBG2
	 */
	public float getInpBG2() {
		return inpBG2;
	}
	/**
	 * @param inpBG2 the inpBG2 to set
	 */
	public void setInpBG2(float inpBG2) {
		this.inpBG2 = inpBG2;
	}
	/**
	 * @return the totOrderCost
	 */
	public float getTotOrderCost() {
		return totOrderCost;
	}
	/**
	 * @param totOrderCost the totOrderCost to set
	 */
	public void setTotOrderCost(float totOrderCost) {
		this.totOrderCost = totOrderCost;
	}
	/**
	 * @return the contigencyGeneral
	 */
	public float getContigencyGeneral() {
		return contigencyGeneral;
	}
	/**
	 * @param contigencyGeneral the contigencyGeneral to set
	 */
	public void setContigencyGeneral(float contigencyGeneral) {
		this.contigencyGeneral = contigencyGeneral;
	}
	/**
	 * @return the agencyCommCharges
	 */
	public float getAgencyCommCharges() {
		return agencyCommCharges;
	}
	/**
	 * @param agencyCommCharges the agencyCommCharges to set
	 */
	public void setAgencyCommCharges(float agencyCommCharges) {
		this.agencyCommCharges = agencyCommCharges;
	}
	/**
	 * @return the ldPerformance
	 */
	public float getLdPerformance() {
		return ldPerformance;
	}
	/**
	 * @param ldPerformance the ldPerformance to set
	 */
	public void setLdPerformance(float ldPerformance) {
		this.ldPerformance = ldPerformance;
	}
	/**
	 * @return the ldforLateDelivery
	 */
	public float getLdforLateDelivery() {
		return ldforLateDelivery;
	}
	/**
	 * @param ldforLateDelivery the ldforLateDelivery to set
	 */
	public void setLdforLateDelivery(float ldforLateDelivery) {
		this.ldforLateDelivery = ldforLateDelivery;
	}
	/**
	 * @return the bankingCharges1
	 */
	public float getBankingCharges1() {
		return bankingCharges1;
	}
	/**
	 * @param bankingCharges1 the bankingCharges1 to set
	 */
	public void setBankingCharges1(float bankingCharges1) {
		this.bankingCharges1 = bankingCharges1;
	}
	/**
	 * @return the bankingCharges2
	 */
	public float getBankingCharges2() {
		return bankingCharges2;
	}
	/**
	 * @param bankingCharges2 the bankingCharges2 to set
	 */
	public void setBankingCharges2(float bankingCharges2) {
		this.bankingCharges2 = bankingCharges2;
	}
	/**
	 * @return the ovrheadSaleCost
	 */
	public float getOvrheadSaleCost() {
		return ovrheadSaleCost;
	}
	/**
	 * @param ovrheadSaleCost the ovrheadSaleCost to set
	 */
	public void setOvrheadSaleCost(float ovrheadSaleCost) {
		this.ovrheadSaleCost = ovrheadSaleCost;
	}
	/**
	 * @return the overRawMaterialCost
	 */
	public float getOverRawMaterialCost() {
		return overRawMaterialCost;
	}
	/**
	 * @param overRawMaterialCost the overRawMaterialCost to set
	 */
	public void setOverRawMaterialCost(float overRawMaterialCost) {
		this.overRawMaterialCost = overRawMaterialCost;
	}
	/**
	 * @return the warrantyCost
	 */
	public float getWarrantyCost() {
		return warrantyCost;
	}
	/**
	 * @param warrantyCost the warrantyCost to set
	 */
	public void setWarrantyCost(float warrantyCost) {
		this.warrantyCost = warrantyCost;
	}
	/**
	 * @return the intrestCharges
	 */
	public float getIntrestCharges() {
		return intrestCharges;
	}
	/**
	 * @param intrestCharges the intrestCharges to set
	 */
	public void setIntrestCharges(float intrestCharges) {
		this.intrestCharges = intrestCharges;
	}
	/**
	 * @return the totalVariableCost
	 */
	public float getTotalVariableCost() {
		return totalVariableCost;
	}
	/**
	 * @param totalVariableCost the totalVariableCost to set
	 */
	public void setTotalVariableCost(float totalVariableCost) {
		this.totalVariableCost = totalVariableCost;
	}
	/**
	 * @return the varNewFlag
	 */
	public boolean isVarNewFlag() {
		return varNewFlag;
	}
	/**
	 * @param varNewFlag the varNewFlag to set
	 */
	public void setVarNewFlag(boolean varNewFlag) {
		this.varNewFlag = varNewFlag;
	}
	/**
	 * @return the varNewCost
	 */
	public float getVarNewCost() {
		return varNewCost;
	}
	/**
	 * @param varNewCost the varNewCost to set
	 */
	public void setVarNewCost(float varNewCost) {
		this.varNewCost = varNewCost;
	}
	/**
	 * @return the projSupply
	 */
	public float getProjSupply() {
		return projSupply;
	}
	/**
	 * @param projSupply the projSupply to set
	 */
	public void setProjSupply(float projSupply) {
		this.projSupply = projSupply;
	}
	/**
	 * @return the projServices
	 */
	public float getProjServices() {
		return projServices;
	}
	/**
	 * @param projServices the projServices to set
	 */
	public void setProjServices(float projServices) {
		this.projServices = projServices;
	}
	/**
	 * @return the projTransport
	 */
	public float getProjTransport() {
		return projTransport;
	}
	/**
	 * @param projTransport the projTransport to set
	 */
	public void setProjTransport(float projTransport) {
		this.projTransport = projTransport;
	}
	/**
	 * @return the totalProjectCost
	 */
	public float getTotalProjectCost() {
		return totalProjectCost;
	}
	/**
	 * @param totalProjectCost the totalProjectCost to set
	 */
	public void setTotalProjectCost(float totalProjectCost) {
		this.totalProjectCost = totalProjectCost;
	}
	/**
	 * @return the supplyCost
	 */
	public float getSupplyCost() {
		return supplyCost;
	}
	/**
	 * @param supplyCost the supplyCost to set
	 */
	public void setSupplyCost(float supplyCost) {
		this.supplyCost = supplyCost;
	}
	/**
	 * @return the serviceCost
	 */
	public float getServiceCost() {
		return serviceCost;
	}
	/**
	 * @param serviceCost the serviceCost to set
	 */
	public void setServiceCost(float serviceCost) {
		this.serviceCost = serviceCost;
	}
	/**
	 * @return the transCost
	 */
	public float getTransCost() {
		return transCost;
	}
	/**
	 * @param transCost the transCost to set
	 */
	public void setTransCost(float transCost) {
		this.transCost = transCost;
	}
	/**
	 * @return the totOrderCostNetProfit
	 */
	public float getTotOrderCostNetProfit() {
		return totOrderCostNetProfit;
	}
	/**
	 * @param totOrderCostNetProfit the totOrderCostNetProfit to set
	 */
	public void setTotOrderCostNetProfit(float totOrderCostNetProfit) {
		this.totOrderCostNetProfit = totOrderCostNetProfit;
	}
	/**
	 * @return the percentProfit
	 */
	public float getPercentProfit() {
		return percentProfit;
	}
	/**
	 * @param percentProfit the percentProfit to set
	 */
	public void setPercentProfit(float percentProfit) {
		this.percentProfit = percentProfit;
	}
	/**
	 * @return the turbineOrderBookCost
	 */
	public float getTurbineOrderBookCost() {
		return turbineOrderBookCost;
	}
	/**
	 * @param turbineOrderBookCost the turbineOrderBookCost to set
	 */
	public void setTurbineOrderBookCost(float turbineOrderBookCost) {
		this.turbineOrderBookCost = turbineOrderBookCost;
	}
	/**
	 * @return the totalFtfCost
	 */
	public float getTotalFtfCost() {
		return totalFtfCost;
	}
	/**
	 * @param totalFtfCost the totalFtfCost to set
	 */
	public void setTotalFtfCost(float totalFtfCost) {
		this.totalFtfCost = totalFtfCost;
	}
	/**
	 * @return the packageCost
	 */
	public float getPackageCost() {
		return packageCost;
	}
	/**
	 * @param packageCost the packageCost to set
	 */
	public void setPackageCost(float packageCost) {
		this.packageCost = packageCost;
	}
	/**
	 * @return the dboMechCost
	 */
	public float getDboMechCost() {
		return dboMechCost;
	}
	/**
	 * @param dboMechCost the dboMechCost to set
	 */
	public void setDboMechCost(float dboMechCost) {
		this.dboMechCost = dboMechCost;
	}
	/**
	 * @return the dboEleCost
	 */
	public float getDboEleCost() {
		return dboEleCost;
	}
	/**
	 * @param dboEleCost the dboEleCost to set
	 */
	public void setDboEleCost(float dboEleCost) {
		this.dboEleCost = dboEleCost;
	}
	/**
	 * @return the projectTotalCost
	 */
	public float getProjectTotalCost() {
		return projectTotalCost;
	}
	/**
	 * @param projectTotalCost the projectTotalCost to set
	 */
	public void setProjectTotalCost(float projectTotalCost) {
		this.projectTotalCost = projectTotalCost;
	}
	/**
	 * @return the projectNewFlag
	 */
	public boolean isProjectNewFlag() {
		return projectNewFlag;
	}
	/**
	 * @param projectNewFlag the projectNewFlag to set
	 */
	public void setProjectNewFlag(boolean projectNewFlag) {
		this.projectNewFlag = projectNewFlag;
	}
	/**
	 * @return the projectNewCost
	 */
	public float getProjectNewCost() {
		return projectNewCost;
	}
	/**
	 * @param projectNewCost the projectNewCost to set
	 */
	public void setProjectNewCost(float projectNewCost) {
		this.projectNewCost = projectNewCost;
	}
	/**
	 * @return the rhsDispInd
	 */
	public int getRhsDispInd() {
		return rhsDispInd;
	}
	/**
	 * @param rhsDispInd the rhsDispInd to set
	 */
	public void setRhsDispInd(int rhsDispInd) {
		this.rhsDispInd = rhsDispInd;
	}
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	/**
	 * @return the colTypeFlag
	 */
	public boolean isColTypeFlag() {
		return colTypeFlag;
	}
	/**
	 * @param colTypeFlag the colTypeFlag to set
	 */
	public void setColTypeFlag(boolean colTypeFlag) {
		this.colTypeFlag = colTypeFlag;
	}
	/**
	 * @return the defaultFlag
	 */
	public int getDefaultFlagNew() {
		return defaultFlagNew;
	}
	/**
	 * @param defaultFlag the defaultFlag to set
	 */
	public void setDefaultFlagNew(int defaultFlagNew) {
		this.defaultFlagNew = defaultFlagNew;
	}
	
	/**
	 * @return the headerText
	 */
	public String getHeaderText() {
		return headerText;
	}
	/**
	 * @param headerText the headerText to set
	 */
	public void setHeaderText(String headerText) {
		this.headerText = headerText;
	}
	/**
	 * @return the footerText
	 */
	public String getFooterText() {
		return footerText;
	}
	/**
	 * @param footerText the footerText to set
	 */
	public void setFooterText(String footerText) {
		this.footerText = footerText;
	}
	/**
	 * @return the desItemId
	 */
	public int getDesItemId() {
		return desItemId;
	}
	/**
	 * @param desItemId the desItemId to set
	 */
	public void setDesItemId(int desItemId) {
		this.desItemId = desItemId;
	}
	
	/**
	 * @return the genInputLink
	 */
	public boolean isGenInputLink() {
		return genInputLink;
	}
	/**
	 * @param genInputLink the genInputLink to set
	 */
	public void setGenInputLink(boolean genInputLink) {
		this.genInputLink = genInputLink;
	}
	/**
	 * @return the desSubItemOrderId
	 */
	public int getDesSubItemOrderId() {
		return desSubItemOrderId;
	}
	/**
	 * @param desSubItemOrderId the desSubItemOrderId to set
	 */
	public void setDesSubItemOrderId(int desSubItemOrderId) {
		this.desSubItemOrderId = desSubItemOrderId;
	}
	/**
	 * @return the desItemName
	 */
	public String getDesItemName() {
		return desItemName;
	}
	/**
	 * @param desItemName the desItemName to set
	 */
	public void setDesItemName(String desItemName) {
		this.desItemName = desItemName;
	}
	/**
	 * @return the eleSpecialId
	 */
	public int getEleSpecialId() {
		return eleSpecialId;
	}
	/**
	 * @param eleSpecialId the eleSpecialId to set
	 */
	public void setEleSpecialId(int eleSpecialId) {
		this.eleSpecialId = eleSpecialId;
	}
	public int getInputCostFlag() {
		return inputCostFlag;
	}
	public void setInputCostFlag(int inputCostFlag) {
		this.inputCostFlag = inputCostFlag;
	}
	public String getGroupDescription() {
		return groupDescription;
	}
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	private float govPrice;
	private float totalGovPrice;
	private float subContrCost;
	private float shopConvCost;
	private float totalF2fCost;
	private float prevSubContrCost;
	private float prevShopConvCost;
	private float prevTotalF2fCost;
	public float getGovPrice() {
		return govPrice;
	}
	public void setGovPrice(float govPrice) {
		this.govPrice = govPrice;
	}
	public float getTotalGovPrice() {
		return totalGovPrice;
	}
	public void setTotalGovPrice(float totalGovPrice) {
		this.totalGovPrice = totalGovPrice;
	}
	public float getSubContrCost() {
		return subContrCost;
	}
	public void setSubContrCost(float subContrCost) {
		this.subContrCost = subContrCost;
	}
	public float getShopConvCost() {
		return shopConvCost;
	}
	public void setShopConvCost(float shopConvCost) {
		this.shopConvCost = shopConvCost;
	}
	public float getTotalF2fCost() {
		return totalF2fCost;
	}
	public void setTotalF2fCost(float totalF2fCost) {
		this.totalF2fCost = totalF2fCost;
	}
	
	public float getPrevSubContrCost() {
		return prevSubContrCost;
	}
	public void setPrevSubContrCost(float prevSubContrCost) {
		this.prevSubContrCost = prevSubContrCost;
	}
	public float getPrevShopConvCost() {
		return prevShopConvCost;
	}
	public void setPrevShopConvCost(float prevShopConvCost) {
		this.prevShopConvCost = prevShopConvCost;
	}
	public float getPrevTotalF2fCost() {
		return prevTotalF2fCost;
	}
	public void setPrevTotalF2fCost(float prevTotalF2fCost) {
		this.prevTotalF2fCost = prevTotalF2fCost;
	}
	private float maxPower;
	public float getMaxPower() {
		return maxPower;
	}
	public void setMaxPower(float maxPower) {
		this.maxPower = maxPower;
	}
	
	private int f2fMastId;
	public int getF2fMastId() {
		return f2fMastId;
	}
	public void setF2fMastId(int f2fMastId) {
		this.f2fMastId = f2fMastId;
	}
	
	private String frmName;
	public String getFrmName() {
		return frmName;
	}
	
	public void setFrmName(String frmName) {
		this.frmName = frmName;
	}
	
	private String eff_from;
	public String getEff_from() {
		return eff_from;
	}
	
	public void setEff_from(String eff_from) {
		this.eff_from = eff_from;
	}
	
	private int default_flag;
	public int getDefault_flag() {
		return default_flag;
	}
	public void setDefault_flag(int default_flag) {
		this.default_flag = default_flag;
	}
	
	private float priceChennai;
	public float getPriceChennai() {
		return priceChennai;
	}
	public void setPriceChennai(float priceChennai) {
		this.priceChennai = priceChennai;
	}
	
	private float priceFob;
	public float getPriceFob() {
		return priceFob;
	}
	public void setPriceFob(float priceFob) {
		this.priceFob = priceFob;
	}
	
	
}
