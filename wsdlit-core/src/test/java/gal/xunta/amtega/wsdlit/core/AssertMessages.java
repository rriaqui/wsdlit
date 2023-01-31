package gal.xunta.amtega.wsdlit.core;

/*-
 * #%L
 * wsdlit-core
 * %%
 * Copyright (C) 2021 - 2022 Axencia para a Modernización Tecnolóxica de Galicia (AMTEGA) - Xunta de Galicia
 * %%
 * This file is part of "wsdlit".
 *
 * "wsdlit" is free software: you can redistribute it and/or modify
 * it under the terms of:
 * European Union Public License, either Version 1.2 or – as soon
 * they will be approved by the European Commission - subsequent versions of
 * the EUPL;
 *
 * "wsdlit" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * European Union Public License for more details.
 *
 * You may obtain a copy of tce European Union Public Licence at:
 * http://joinup.ec.europa.eu/software/page/eupl/licence-eupl
 * #L%
 */

/**
 * Assert messages for test cases.
 *
 * @author rriaqui
 * @since 1.0.0
 */
public final class AssertMessages {
    public static final String ALL_EXPECTED = "Esperábase un modelo de grupo ALL.";
    public static final String ARRAY_EQUALS_EXPECTED = "O array non é o esperado.";
    public static final String CHOICE_EXPECTED = "Esperábase un modelo de grupo CHOICE.";
    public static final String CYCLIC_REFERENCE_EXPECCTED = "Esperábase unha referencia cíclica";

    public static final String EQUALS_EXPECTED = "Os valores deberían ser iguais.";

    public static final String EXCEPTION_EXPECTED = "Esperábase unha excepción";
    public static final String NOT_EQUALS_EXPECTED = "Non se esperaba o mesmo valor.";
    public static final String FALSE_EXPECTED = "Esperábase o valor `false`";

    public static final String FILE_SHOULD_EXISTS = "O arquivo debería existir.";
    public static final String FILE_NOT_EXPECTED = "O valor do File non é o esperado.";
    public static final String GROUP_EXPECTED = "Esperábase un modelo de grupo.";
    public static final String NOT_NULL_EXPECTED = "Esperábase un valor non nulo";
    public static final String NULL_EXPECTED = "Esperábase un valor nulo";
    public static final String SAME_EXPECTED = "Esperábase a mesma instancia do obxecto.";

    public static final String SEQUENCE_EXPECTED = "Esperábase un modelo de grupo SEQUENCE.";
    public static final String SIZE_NOT_EXPECTED = "O número total de elementos non é o esperado.";
    public static final String EQUALITY_SYMETRIC_EXPECTED = "Esperábase que o equals repectase o symetric.";
    public static final String TRUE_EXPECTED = "Esperábase o valor `true`.";
    public static final String URL_NOT_EXPECTED = "O valor da URL non é o esperado";
    public static final String XSD_TYPE_NOT_EXPECTED = "O tipo de datos non é o esperado.";


    private AssertMessages() {}
}
