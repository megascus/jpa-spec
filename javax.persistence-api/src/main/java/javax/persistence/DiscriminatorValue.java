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

/**
 * 与えられた型のエンティティのための識別列の値を指定します。(DiscriminatorValue:識別値)
 * 
 * <p> <code>DiscriminatorValue</code>アノテーションは具象型のエンティティクラスでのみ指定できます。
 * 
 * <p> <code>DiscriminatorValue</code>アノテーションが指定されておらず、識別列が使用されている場合はプロバイダ固有の関数を使用してエンティティ型を表す値を生成します。
 * {@link DiscriminatorType}が<code>STRING</code>の場合、識別のデフォルト値はエンティティ名です。
 * 
 * <p> 継承戦略と識別列は、異なる継承戦略が適用されるエンティティクラス階層またはサブ階層のルートでのみ指定されます。 
 * デフォルトでない場合の識別値は階層内の各エンティティクラスに指定する必要があります。
 *
 * <pre>
 *
 *    Example:
 *
 *    &#064;Entity
 *    &#064;Table(name="CUST")
 *    &#064;Inheritance(strategy=SINGLE_TABLE)
 *    &#064;DiscriminatorColumn(name="DISC", discriminatorType=STRING, length=20)
 *    &#064;DiscriminatorValue("CUSTOMER")
 *    public class Customer { ... }
 *
 *    &#064;Entity
 *    &#064;DiscriminatorValue("VCUSTOMER")
 *    public class ValuedCustomer extends Customer { ... }
 * </pre>
 *
 * @see DiscriminatorColumn
 *
 * @since Java Persistence 1.0
 */
@Target({TYPE}) 
@Retention(RUNTIME)

public @interface DiscriminatorValue {

    /**
     * (オプション)行がアノテーション付きのエンティティ型のエンティティであることを示す値。
     * 
     * <code>DiscriminatorValue</code>アノテーションが指定されておらず、識別列が使用されている場合はプロバイダ固有の関数を使用してエンティティ型を表す値を生成します。
     * <code>DiscriminatorType</code>が<code>STRING</code>の場合、識別のデフォルト値はエンティティ名です。
     */
    String value();
}
