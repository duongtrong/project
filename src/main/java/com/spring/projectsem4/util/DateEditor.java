package com.spring.projectsem4.util;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String ret = sdf.format(new Date());
        Object value = this.getValue();
        if (value != null) {
            if (value instanceof Date) {
                ret = sdf.format((Date) value);
            }
        }
        return ret;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = sdf.parse(text);
        } catch (ParseException e) {
            date = new Date();
        } finally {
            this.setValue(date);
        }
    }
}
