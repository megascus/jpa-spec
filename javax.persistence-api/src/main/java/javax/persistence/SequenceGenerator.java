/*******************************************************************************
 * Copyright (c) 2008 - 2017 Oracle Corporation. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *     Lukas Jungmann  - Java Persistence 2.2
 *     Linda DeMichiel - Java Persistence 2.1
 *     Linda DeMichiel - Java Persistence 2.0
 *
 ******************************************************************************/ 
package javax.persistence;

import java.lang.annotation.Target;
import java.lang.annotation.Retention;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;

/**
 * {@link GeneratedValue}アノテーションにgenerator要素が指定されたときに、名前で参照される主キージェネレーターを定義します。
 * 
 * シーケンスジェネレーターはエンティティクラスまたは主キーのフィールドまたはプロパティで指定できます。
 * ジェネレーター名のスコープは、永続化ユニット(すべてのジェネレーターの型全体)に対してグローバルです。
 *
 * <pre>
 *   Example:
 *
 *   &#064;SequenceGenerator(name="EMP_SEQ", allocationSize=25)
 * </pre>
 *
 * @since Java Persistence 1.0
 */
@Repeatable(SequenceGenerators.class)
@Target({TYPE, METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface SequenceGenerator {

    /** 
     * (必須) 1つ以上のクラスが主キー値のジェネレーターとして参照できるジェネレーターの一意な名前。
     */
    String name();

    /**
     * (オプション) 主キーの値を取得するデータベースのシーケンスオブジェクトの名前。
     * 
     * <p>デフォルトではデータベースが設定した値になります。
     */
    String sequenceName() default "";

    /** (オプション) シーケンスジェネレーターの含まれるカタログ。
     *
     * @since Java Persistence 2.0
     */
    String catalog() default "";

    /** (オプション) シーケンスジェネレーターの含まれるスキーマ。
     *
     * @since Java Persistence 2.0
     */
    String schema() default "";

    /** 
     * (オプション) シーケンスオブジェクトの生成を始める値
     */
    int initialValue() default 1;

    /**
     * (オプション) シーケンスからシーケンス番号を割り当てるときに増加する量。
     */
    int allocationSize() default 50;
}
