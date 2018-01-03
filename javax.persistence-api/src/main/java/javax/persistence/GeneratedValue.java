/*******************************************************************************
 * Copyright (c) 2008 - 2013 Oracle Corporation. All rights reserved.
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
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/
package javax.persistence;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.GenerationType.AUTO;

/**
 * 主キーの値の生成戦略の指定を提供します。
 * 
 * <p><code>GeneratedValue</code> アノテーションは{@link Id}アノテーションとともにエンティティまたはマップドスーパークラスの主キーのプロパティまたはフィールドに適用できます。
 * <code>GeneratedValue</code>アノテーションは単純な主キーに対しての用法のみサポートされる必要があります。
 * 派生した主キーでは<code>GeneratedValue</code>アノテーションの使用はサポートされていません。
 * Provides for the specification of generation strategies for the
 * values of primary keys. 
 *
 * <pre>
 *
 *     Example 1:
 *
 *     &#064;Id
 *     &#064;GeneratedValue(strategy=SEQUENCE, generator="CUST_SEQ")
 *     &#064;Column(name="CUST_ID")
 *     public Long getId() { return id; }
 *
 *     Example 2:
 *
 *     &#064;Id
 *     &#064;GeneratedValue(strategy=TABLE, generator="CUST_GEN")
 *     &#064;Column(name="CUST_ID")
 *     Long id;
 * </pre>
 *
 * @see Id
 * @see TableGenerator
 * @see SequenceGenerator
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD})
@Retention(RUNTIME)

public @interface GeneratedValue {

    /**
     * (オプション) アノテーションの付いたエンティティの主キーを生成するために永続性プロバイダが使用しなければならない主キー生成戦略。
     */
    GenerationType strategy() default AUTO;

    /**
     * (オプション) {@link SequenceGenerator} アノテーションや{@link TableGenerator}アノテーションで指定された使用する主キージェネレーターの名前。
     * 
     * <p> デフォルトでは永続性プロバイダによって提供されるIDジェネレータになります。
     */
    String generator() default "";
}
