package ch.akop.weathercloud.scraper.weathercloud;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Response(

		@JsonProperty("solarrad_month_hours")
		TimestampPair solarradMonthHours,

		@JsonProperty("wdiravg_day_max")
		TimestampPair wdiravgDayMax,

		@JsonProperty("wdir_month_min")
		TimestampPair wdirMonthMin,

		@JsonProperty("bar_year_min")
		TimestampPair barYearMin,

		@JsonProperty("wspdhi_day_min")
		TimestampPair wspdhiDayMin,

		@JsonProperty("wdiravg_month_max")
		TimestampPair wdiravgMonthMax,

		@JsonProperty("rain_year_max")
		TimestampPair rainYearMax,

		@JsonProperty("hum_month_min")
		TimestampPair humMonthMin,

		@JsonProperty("uvi_current")
		TimestampPair uviCurrent,

		@JsonProperty("temp_day_min")
		TimestampPair tempDayMin,

		@JsonProperty("dew_year_max")
		TimestampPair dewYearMax,

		@JsonProperty("wspdhi_year_max")
		TimestampPair wspdhiYearMax,

		@JsonProperty("wdiravg_day_min")
		TimestampPair wdiravgDayMin,

		@JsonProperty("wdir_month_max")
		TimestampPair wdirMonthMax,

		@JsonProperty("wspdhi_day_max")
		TimestampPair wspdhiDayMax,

		@JsonProperty("last_update")
		int lastUpdate,

		@JsonProperty("solarrad_year_max")
		TimestampPair solarradYearMax,

		@JsonProperty("temp_day_max")
		TimestampPair tempDayMax,

		@JsonProperty("wspdavg_current")
		TimestampPair wspdavgCurrent,

		@JsonProperty("chill_month_max")
		TimestampPair chillMonthMax,

		@JsonProperty("uvi_month_min")
		TimestampPair uviMonthMin,

		@JsonProperty("rainrate_day_min")
		TimestampPair rainrateDayMin,

		@JsonProperty("solarrad_day_hours")
		TimestampPair solarradDayHours,

		@JsonProperty("solarrad_month_max")
		TimestampPair solarradMonthMax,

		@JsonProperty("rainrate_day_max")
		TimestampPair rainrateDayMax,

		@JsonProperty("hum_year_min")
		TimestampPair humYearMin,

		@JsonProperty("wspdavg_day_max")
		TimestampPair wspdavgDayMax,

		@JsonProperty("dew_year_min")
		TimestampPair dewYearMin,

		@JsonProperty("hum_month_max")
		TimestampPair humMonthMax,

		@JsonProperty("rainrate_month_max")
		TimestampPair rainrateMonthMax,

		@JsonProperty("dew_month_min")
		TimestampPair dewMonthMin,

		@JsonProperty("solarrad_year_hours")
		TimestampPair solarradYearHours,

		@JsonProperty("chill_day_min")
		TimestampPair chillDayMin,

		@JsonProperty("chill_month_min")
		TimestampPair chillMonthMin,

		@JsonProperty("rainrate_month_min")
		TimestampPair rainrateMonthMin,

		@JsonProperty("wdiravg_year_max")
		TimestampPair wdiravgYearMax,

		@JsonProperty("hum_year_max")
		TimestampPair humYearMax,

		@JsonProperty("dew_month_max")
		TimestampPair dewMonthMax,

		@JsonProperty("wspdavg_month_max")
		TimestampPair wspdavgMonthMax,

		@JsonProperty("wspdavg_month_min")
		TimestampPair wspdavgMonthMin,

		@JsonProperty("chill_current")
		TimestampPair chillCurrent,

		@JsonProperty("wspdhi_month_max")
		TimestampPair wspdhiMonthMax,

		@JsonProperty("rain_month_max")
		TimestampPair rainMonthMax,

		@JsonProperty("hum_current")
		TimestampPair humCurrent,

		@JsonProperty("wdiravg_year_min")
		TimestampPair wdiravgYearMin,

		@JsonProperty("hum_day_max")
		TimestampPair humDayMax,

		@JsonProperty("wspdhi_month_min")
		TimestampPair wspdhiMonthMin,

		@JsonProperty("chill_day_max")
		TimestampPair chillDayMax,

		@JsonProperty("wspdavg_year_max")
		TimestampPair wspdavgYearMax,

		@JsonProperty("uvi_year_max")
		TimestampPair uviYearMax,

		@JsonProperty("hum_day_min")
		TimestampPair humDayMin,

		@JsonProperty("dew_current")
		TimestampPair dewCurrent,

		@JsonProperty("solarrad_day_max")
		TimestampPair solarradDayMax,

		@JsonProperty("rainrate_current")
		TimestampPair rainrateCurrent,

		@JsonProperty("wspdavg_year_min")
		TimestampPair wspdavgYearMin,

		@JsonProperty("uvi_day_min")
		TimestampPair uviDayMin,

		@JsonProperty("uvi_year_min")
		TimestampPair uviYearMin,

		@JsonProperty("bar_day_max")
		TimestampPair barDayMax,

		@JsonProperty("rainrate_year_min")
		TimestampPair rainrateYearMin,

		@JsonProperty("temp_current")
		TimestampPair tempCurrent,

		@JsonProperty("uvi_day_max")
		TimestampPair uviDayMax,

		@JsonProperty("rain_day_total")
		TimestampPair rainDayTotal,

		@JsonProperty("wspd_month_min")
		TimestampPair wspdMonthMin,

		@JsonProperty("wspdhi_current")
		TimestampPair wspdhiCurrent,

		@JsonProperty("rain_day_max")
		TimestampPair rainDayMax,

		@JsonProperty("rain_month_total")
		TimestampPair rainMonthTotal,

		@JsonProperty("bar_day_min")
		TimestampPair barDayMin,

		@JsonProperty("wspd_month_max")
		TimestampPair wspdMonthMax,

		@JsonProperty("wdir_year_max")
		TimestampPair wdirYearMax,

		@JsonProperty("rain_year_total")
		TimestampPair rainYearTotal,

		@JsonProperty("temp_year_min")
		TimestampPair tempYearMin,

		@JsonProperty("wspd_day_min")
		TimestampPair wspdDayMin,

		@JsonProperty("temp_month_min")
		TimestampPair tempMonthMin,

		@JsonProperty("wdir_year_min")
		TimestampPair wdirYearMin,

		@JsonProperty("wspd_year_min")
		TimestampPair wspdYearMin,

		@JsonProperty("rain_current")
		TimestampPair rainCurrent,

		@JsonProperty("wdir_current")
		TimestampPair wdirCurrent,

		@JsonProperty("wspdavg_day_min")
		TimestampPair wspdavgDayMin,

		@JsonProperty("uvi_month_max")
		TimestampPair uviMonthMax,

		@JsonProperty("wspd_day_max")
		TimestampPair wspdDayMax,

		@JsonProperty("temp_month_max")
		TimestampPair tempMonthMax,

		@JsonProperty("wdiravg_current")
		TimestampPair wdiravgCurrent,

		@JsonProperty("rainrate_year_max")
		TimestampPair rainrateYearMax,

		@JsonProperty("bar_current")
		TimestampPair barCurrent,

		@JsonProperty("dew_day_max")
		TimestampPair dewDayMax,

		@JsonProperty("wspd_current")
		TimestampPair wspdCurrent,

		@JsonProperty("wspd_year_max")
		TimestampPair wspdYearMax,

		@JsonProperty("bar_month_max")
		TimestampPair barMonthMax,

		@JsonProperty("wdir_day_max")
		TimestampPair wdirDayMax,

		@JsonProperty("chill_year_max")
		TimestampPair chillYearMax,

		@JsonProperty("solarrad_current")
		TimestampPair solarradCurrent,

		@JsonProperty("bar_year_max")
		TimestampPair barYearMax,

		@JsonProperty("temp_year_max")
		TimestampPair tempYearMax,

		@JsonProperty("bar_month_min")
		TimestampPair barMonthMin,

		@JsonProperty("wdir_day_min")
		TimestampPair wdirDayMin,

		@JsonProperty("wdiravg_month_min")
		TimestampPair wdiravgMonthMin,

		@JsonProperty("chill_year_min")
		TimestampPair chillYearMin,

		@JsonProperty("wspdhi_year_min")
		TimestampPair wspdhiYearMin,

		@JsonProperty("dew_day_min")
		TimestampPair dewDayMin
) {
}
