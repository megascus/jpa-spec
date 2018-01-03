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

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * スキーマ生成でインデックスの生成を指定するのに使用されます。
 * 
 * <p>
 * 主キーに対するインデックスは自動的に生成されるため、主キーに対するインデックスの指定は必要ないことに注意してください。
 *
 * <p> 
 * <code>columnList</code>要素の構文は次のような<code>column_list</code>です。:
 * 
 * <pre>
 *    column::= index_column [,index_column]*
 *    index_column::= column_name [ASC | DESC]
 * </pre>
 * 
 * <p> <code>ASC</code>も<code>DESC</code>も指定されていない場合、<code>ASC</code>(昇順)が仮定されます。
 *
 * @see Table
 * @see SecondaryTable
 * @see CollectionTable
 * @see JoinTable
 * @see TableGenerator
 *
 * @since Java Persistence 2.1
 *
 */
@Target({})
@Retention(RUNTIME)
public @interface Index {

    /**
     * (オプション) インデックスの名前、デフォルトはプロバイダーが生成する名前です。
     */
    String name() default "";

    /**
     * (オプション)インデックスや順序に含まれる列の名前。
     */
    String columnList();

    /**
     * (オプション) インデックスがユニークかどうか。
     */
    boolean unique() default false;

}
