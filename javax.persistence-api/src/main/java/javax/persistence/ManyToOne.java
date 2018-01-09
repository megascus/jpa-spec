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
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static javax.persistence.FetchType.EAGER;

/**
 * 多対1の多重度を持つ別のエンティティへの単一値の関連付けを指定します。
 * 
 * 通常は関連する対象のエンティティは参照されているオブジェクトの型から推論できるため明示的に指定する必要はありません。
 * 関係が双方向である場合、被所有側の<code>OneToMany</code>エンティティでは<code>mappedBy</code>要素を使用して、
 * 所有側のエンティティの関係を示すエンティティのフィールドまたはプロパティを指定する必要があります。
 * 
 * <p> <code>ManyToOne</code>アノテーションは組み込みクラス({@link Embeddable}の付いたクラス)からエンティティクラスへの関係を指定するために組み込みクラス内で使用できます。
 * 関係が双方向である場合、被所有側の<code>OneToMany</code>エンティティでは<code>OneToMany</code>アノテーションの<code>mappedBy</code>要素を使用して
 * 所有側のエンティティの関係を示すフィールドまたはプロパティを指定する必要があります。
 * 組み込み属性内の関係属性を示すには、<code>mappedBy</code>要素でドット(".")表記を使用する必要があります。
 * ドット表記で使用される各識別子の値は、それぞれの組み込みフィールドまたはプロパティの名前です。
 * 
 * <pre>
 *
 *     Example 1:
 *
 *     &#064;ManyToOne(optional=false) 
 *     &#064;JoinColumn(name="CUST_ID", nullable=false, updatable=false)
 *     public Customer getCustomer() { return customer; }
 *
 *
 *     Example 2:
 * 
 *     &#064;Entity
 *        public class Employee {
 *        &#064;Id int id;
 *        &#064;Embedded JobInfo jobInfo;
 *        ...
 *     }
 *
 *     &#064;Embeddable
 *        public class JobInfo {
 *        String jobDescription; 
 *        &#064;ManyToOne ProgramManager pm; // Bidirectional
 *     }
 *
 *     &#064;Entity
 *        public class ProgramManager {
 *        &#064;Id int id;
 *        &#064;OneToMany(mappedBy="jobInfo.pm")
 *        Collection&#060;Employee&#062; manages;
 *     }
 *
 * </pre>
 *
 * @since Java Persistence 1.0
 */
@Target({METHOD, FIELD}) 
@Retention(RUNTIME)

public @interface ManyToOne {

    /** 
     * (オプション) 関連付けの対象となるエンティティクラス。
     *
     * <p> デフォルトでは関連付けを格納するフィールドまたはプロパティの型になります。
     */
    Class targetEntity() default void.class;

    /**
     * (オプション) 関連付けの対象にカスケードする必要のある操作。
     *
     * <p> デフォルトではすべての操作はカスケードされません。
     */
    CascadeType[] cascade() default {};

    /** 
     * (オプション) 関連付けを遅延ロード(LAZY)するか、即座に取得(EAGER)する必要があるかどうか。
     * 
     * EAGER戦略は永続化プロバイダの実行時に関連エンティティを即座に取得するべきだとする要件です。
     * LAZY戦略は永続化プロバイダの実行時のヒントです。
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
}
