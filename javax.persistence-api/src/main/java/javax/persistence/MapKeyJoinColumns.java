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
import static javax.persistence.ConstraintMode.PROVIDER_DEFAULT;

/**
 * エンティティを参照する複合マップキーをサポートします。
 * 
 * <p> <code>MapKeyJoinColumns</code>アノテーションは<code>MapKeyJoinColumn</code>アノテーションをまとめます。
 * <code>MapKeyJoinColumns</code>アノテーションを使用する場合、
 * まとめられた<code>MapKeyJoinColumn</code>アノテーションごとに<code>name</code>と<code>referencedColumnName</code>要素の両方を指定する必要があります。
 * 
 * @see MapKeyJoinColumn
 * @see ForeignKey
 * 
 * @since Java Persistence 2.0
 */
@Target( { METHOD, FIELD })
@Retention(RUNTIME)
public @interface MapKeyJoinColumns {
	/**
	 * (必須) マップキーのエンティティをマッピングするのに使用されるマップキー結合カラム。
	 */
	MapKeyJoinColumn[] value();

        /**
         *  (オプション) テーブル作成が有効なときに作成される外部キー制約を指定もしくは制御するために使用されます。
         * 
         *  この要素といずれかの<code>MapKeyJoinColumn</code>要素内の<code>foreignKey</code>要素の両方が指定されていた場合のふるまいは定義されません。
         *  両方の場所で外部キーアノテーション要素が指定されていない場合は永続化プロバイダのデフォルトの外部キー戦略が適用されます。
         *
         *  @since Java Persistence 2.1
         */
        ForeignKey foreignKey() default @ForeignKey(PROVIDER_DEFAULT);
}
