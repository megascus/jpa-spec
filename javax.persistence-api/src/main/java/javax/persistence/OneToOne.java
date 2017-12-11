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
import javax.persistence.CascadeType;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.FetchType.EAGER;

/**
 * 1対1の多重度を持つ別のエンティティへの単一値の関連付けを指定します。
 * 
 * 通常は関連するターゲットエンティティは参照されているオブジェクトの型から推論できるため明示的に指定する必要はありません。
 * 関係が双方向である場合、被所有側のエンティティは<code>OneToOne</code>アノテーションの<code>mappedBy</code>要素を使用して、
 * 所有側のエンティティの関係を示すフィールドまたはプロパティを指定する必要があります。
 * 
 * <p> <code>OneToOne</code>アノテーションは組み込みクラス({@link Embeddable}の付いたクラス)からエンティティクラスへの関係を指定するために組み込みクラス内で使用できます。
 * 関係が双方向であり、組み込みクラスを含むエンティティが関係の所有側のエンティティである場合、
 * 被所有側のエンティティでは、組み込みクラスの関係を示すフィールドまたはプロパティを指定するために<code>mappedBy</code>アノテーションの<code>mappedBy</code>要素を使用する必要があります。
 * 組み込み属性内のリレーションシップ属性を示すには、<code>mappedBy</code>要素でドット(".")表記を使用する必要があります。
 * ドット表記で使用される各識別子の値は、それぞれの組み込みフィールドまたはプロパティの名前です。
 * 
 * <pre>
 *    Example 1: 外部キー列を使用してマッピングする1対1の関連付け
 *
 *    // On Customer class:
 *
 *    &#064;OneToOne(optional=false)
 *    &#064;JoinColumn(
 *    	name="CUSTREC_ID", unique=true, nullable=false, updatable=false)
 *    public CustomerRecord getCustomerRecord() { return customerRecord; }
 *
 *    // On CustomerRecord class:
 *
 *    &#064;OneToOne(optional=false, mappedBy="customerRecord")
 *    public Customer getCustomer() { return customer; }
 *
 *
 *    Example 2: ソースとターゲットの両方が同じ主キー値を共有することを前提とした1対1の関連付け
 *
 *    // On Employee class:
 *
 *    &#064;Entity
 *    public class Employee {
 *    	&#064;Id Integer id;
 *    
 *    	&#064;OneToOne &#064;MapsId
 *    	EmployeeInfo info;
 *    	...
 *    }
 *
 *    // On EmployeeInfo class:
 *
 *    &#064;Entity
 *    public class EmployeeInfo {
 *    	&#064;Id Integer id;
 *    	...
 *    }
 *
 *
 *    Example 3: 組み込みクラスから別のエンティティへの1対1の関連付け
 *
 *    &#064;Entity
 *    public class Employee {
 *       &#064;Id int id;
 *       &#064;Embedded LocationDetails location;
 *       ...
 *    }
 *
 *    &#064;Embeddable
 *    public class LocationDetails {
 *       int officeNumber;
 *       &#064;OneToOne ParkingSpot parkingSpot;
 *       ...
 *    }
 *
 *    &#064;Entity
 *    public class ParkingSpot {
 *       &#064;Id int id;
 *       String garage;
 *       &#064;OneToOne(mappedBy="location.parkingSpot") Employee assignedTo;
 *        ... 
 *    } 
 *
 * </pre>
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)

public @interface OneToOne {

    /** 
     * (オプション) 関連付けの対象となるエンティティクラス。
     *
     * <p> デフォルトでは関連付けを格納するフィールドまたはプロパティの型になります。
     */
    Class targetEntity() default void.class;

    /**
     * (オプション) 関連付けのターゲットにカスケードする必要のある操作。
     *
     * <p> デフォルトではすべての操作はカスケードされません。
     */
    CascadeType[] cascade() default {};

    /** 
     * (オプション) 関連付けを遅延ロード(LAZY)するか、即座に取得(EAGER)する必要があるかどうか。
     * 
     * EAGER戦略は永続性プロバイダーの実行時に関連エンティティを即座に取得するべきだとする要件です。
     * LAZY戦略は持続性プロバイダーの実行時のヒントです。
     * 
     * <p>訳注：つまりEAGERは必ず有効になり、LAZYが有効になるかは実装に依存します。
     */
    FetchType fetch() default EAGER;

    /** 
     * (オプション) 関連付けがオプションかどうか。
     * 
     * もしfalseである場合、この関連は常にnullでない必要があります。
     */
    boolean optional() default true;

    /** (オプション) 関係を所有するフィールド。
     * 
     * この要素は、関連の逆(被所有)側でのみ指定されます。
     */
    String mappedBy() default "";


    /**
     * (オプション) 関係から削除されたエンティティに対し削除操作を適用し削除操作をそれらのエンティティにカスケードするかどうか。
     * @since Java Persistence 2.0
     */
    boolean orphanRemoval() default false;
}
