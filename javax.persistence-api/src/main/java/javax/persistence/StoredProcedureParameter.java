/*******************************************************************************
 * Copyright (c) 2011 - 2013 Oracle Corporation. All rights reserved.
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

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 名前付きストアドプロシージャーのクエリーのパラメーターを指定します。
 * 
 * 名前付きストアドプロシージャーのクエリーのパラメーターはすべて指定される必要があります。
 *
 * @see NamedStoredProcedureQuery
 * @see ParameterMode 
 *
 * @since Java Persistence 2.1
 */
@Target({}) 
@Retention(RUNTIME)
public @interface StoredProcedureParameter { 

    /** 
     *  データベース内のストアドプロシージャーによって定義されたパラメータの名前。
     * 
     *  名前が指定されていない場合、ストアードプロシージャーは位置指定のパラメーターを使用するものとみなされます。
     */
    String name() default "";

    /**
     *  パラメーターがINまたはINOUT、OUT、REF_CURSORのいずれであるかを指定します。
     * 
     *  REF_CURSORパラメータは、ストアドプロシージャーから結果セットを返すために、一部のデータベースで使用されます。
     */
    ParameterMode mode() default ParameterMode.IN;

    /** パラメーターのJDBC型 */
    Class type();

}
