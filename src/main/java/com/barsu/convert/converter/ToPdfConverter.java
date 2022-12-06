package com.barsu.convert.converter;

import com.barsu.convert.entity.Convert;
import com.itextpdf.text.Document;

public interface ToPdfConverter {

    Document convert(Convert convert);
}
