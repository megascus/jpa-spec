/*******************************************************************************
 * Copyright (c) 2011 - 2017 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Linda DeMichiel - Java Persistence 2.1
 *
 ******************************************************************************/ 
package javax.persistence; 

/**
 * ストアドプロシージャのクエリーのパラメーターのモードを指定します。
 *
 * @see StoredProcedureQuery
 * @see StoredProcedureParameter
 *
 * @since Java Persistence 2.1
 */
public enum ParameterMode {

    /**
     * ストアドプロシージャの入力パラメーター
     */
    IN,

    /**
     * ストアドプロシージャの入力/出力パラメーター
     */
    INOUT,

    /**
     * ストアドプロシージャの出力パラメーター
     */
    OUT,

    /**
     * ストアドプロシージャの参照カーソルパラメーター。
     * 
     * いくつかのデータベースではREF_CURSORパラメーターをストアドプロシージャからの結果セットを戻すために使用できます。
     */
    REF_CURSOR,

}
