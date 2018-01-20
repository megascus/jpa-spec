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
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.DiscriminatorType.STRING;

/**
 * <code>SINGLE_TABLE</code>、<code>JOINED</code> {@link Inheritance} マッピング戦略の識別カラムを指定します。
 * 
 * <p> 継承戦略と識別カラムは、異なる継承戦略が適用されるエンティティクラス階層またはサブ階層のルートでのみ指定されます。 
 * 
 * <p> <code>DiscriminatorValue</code>アノテーションが指定されておらず、識別カラムが必須な場合は、識別カラムのデフォルトの名前は<code>"DTYPE"</code>になり、
 * 識別型は{@link DiscriminatorType#STRING DiscriminatorType.STRING}となります。
 *
 * <pre>
 *     Example:
 *
 *     &#064;Entity
 *     &#064;Table(name="CUST")
 *     &#064;Inheritance(strategy=SINGLE_TABLE)
 *     &#064;DiscriminatorColumn(name="DISC", discriminatorType=STRING, length=20)
 *     public class Customer { ... }
 *
 *     &#064;Entity
 *     public class ValuedCustomer extends Customer { ... }
 * </pre>
 *
 * @see DiscriminatorValue
 *
 * @since Java Persistence 1.0
 */
@Target({TYPE}) 
@Retention(RUNTIME)

public @interface DiscriminatorColumn {

    /**
     * (オプション) 識別に使用されるカラムの名前。
     */
    String name() default "DTYPE";

    /**
     * (オプション) クラスの識別に使用するオブジェクト/カラムの型。
     * 
     * デフォルトは{@link DiscriminatorType#STRING DiscriminatorType.STRING}です。
     */
    DiscriminatorType discriminatorType() default STRING;

    /**
     * (オプション)識別カラムのDDLを生成するときに使用されるSQLフラグメント。
     * 
     * <p> デフォルトではプロバイダ生成のSQLが使用され、指定された識別型のカラムが作成されます。
     */
    String columnDefinition() default "";

    /** 
     * (オプション) 文字列ベースの識別型のカラムの長さ。
     * 
     * 他の識別型では無視されます。
     */
    int length() default 31;
}
