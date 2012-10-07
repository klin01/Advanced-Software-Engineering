#!/usr/bin/env python


import sqlite3

import db_info


conn = sqlite3.connect(db_info.DB_NAME)

c = conn.cursor()

c.execute("CREATE TABLE %s (region text, trend text, int occur)" % db_info.TREND_TABLE)

conn.commit()

conn.close()

