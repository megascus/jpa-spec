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
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Repeatable;

/**
 * {@link GeneratedValue}アノテーションにgenerator要素が指定されたときに、名前で参照される主キージェネレーターを定義します。
 * 
 * テーブルジェネレーターはエンティティクラスまたは主キーのフィールドまたはプロパティで指定できます。
 * ジェネレーター名のスコープは、永続性ユニット(すべてのジェネレーターの型全体)に対してグローバルです。
 *
 * <pre>
 *    Example 1:
 *    
 *    &#064;Entity public class Employee {
 *        ...
 *        &#064;TableGenerator(
 *            name="empGen", 
 *            table="ID_GEN", 
 *            pkColumnName="GEN_KEY", 
 *            valueColumnName="GEN_VALUE", 
 *            pkColumnValue="EMP_ID", 
 *            allocationSize=1)
 *        &#064;Id
 *        &#064;GeneratedValue(strategy=TABLE, generator="empGen")
 *        int id;
 *        ...
 *    }
 *    
 *    Example 2:
 *    
 *    &#064;Entity public class Address {
 *        ...
 *        &#064;TableGenerator(
 *            name="addressGen", 
 *            table="ID_GEN", 
 *            pkColumnName="GEN_KEY", 
 *            valueColumnName="GEN_VALUE", 
 *            pkColumnValue="ADDR_ID")
 *        &#064;Id
 *        &#064;GeneratedValue(strategy=TABLE, generator="addressGen")
 *        int id;
 *        ...
 *    }
 * </pre>
 *
 * @see GeneratedValue
 *
 * @since Java Persistence 1.0
 */
@Repeatable(TableGenerators.class)
@Target({TYPE, METHOD, FIELD}) 
@Retention(RUNTIME)
public @interface TableGenerator {

    /** 
     * (必須) 1つ以上のクラスがID値のジェネレーターとして参照できるジェネレーターの一意な名前。
     */
    String name();

    /** 
     * (オプション) 生成されたID値を格納するテーブルの名前。
     * 
     * <p>デフォルトでは永続性プロバイダによって指定された名前になります。
     */
    String table() default "";

    /** 
     * (オプション) テーブルの含まれるカタログ。
     * 
     * <p>既定のカタログがデフォルト値です。
     */
    String catalog() default "";

    /** 
     * (オプション) テーブルの含まれるスキーマ。
     * 
     * <p>ユーザーにとっての既定のスキーマがデフォルト値です。
     */
    String schema() default "";

    /** 
     * (オプション) テーブル内の主キーの列の名前。
     * 
     * <p>デフォルトでは永続性プロバイダによって選ばれます。
     */
    String pkColumnName() default "";

    /** 
     * (オプション) 生成された最後の値を格納する列の名前。
     * 
     * <p>デフォルトでは永続性プロバイダによって選ばれます。
     */
    String valueColumnName() default "";

    /**
     * (オプション) 生成された値のセットをテーブルに格納されている可能性のある他のものと区別するジェネレーターテーブルの主キーの値。
     * 
     * <p>デフォルトでは、永続性プロバイダによって選ばれた値がジェネレーターテーブルの主キーの列に格納されます
     */
    String pkColumnValue() default "";

    /** 
     * (オプション) 生成された最後の値を格納する列を初期化するために使用される初期値。
     */
    int initialValue() default 0;

    /**
     * (オプション) ジェネレーターが生成した値からID番号を割り当てるときにインクリメントする量。
     */
    int allocationSize() default 50;

    /**
     * (オプション) テーブルに配置される固有の制約。
     * 
     * これらはテーブルの生成が有効な場合にのみ使用されます。これらの制約は主キーの制約とは別に適用されます。
     * 
     * <p>デフォルトでは、追加の制約はありません。
     */
    UniqueConstraint[] uniqueConstraints() default {};

    /**
     * (オプション) テーブルのためのインデックス。
     * 
     * これらはテーブルの生成が有効な場合にのみ使用されます。
     * 主キー索引は自動的に作成されるため、主キーの索引を指定する必要はないことに注意してください。
     *
     * @since Java Persistence 2.1 
     */
    Index[] indexes() default {};
}
