Manually scheduling:
     
- Kube Scheduler, schedule pods to nodes
- every pod has a field called node name which is not set when you create pod menifest file, k8s adds this automatically.
- Kube schedule looks at all the pods which do not have the 'node name' property set.
- If a pod does not have a node name set in the property, it schedule pods to a node. 
- if there is no scheduler you would need to manually assign pods to node yourself
     - so in the pod manifest, you can simply add the node name field and assign the pod to the node. 
- You can only specify the node name in creation time and not once created. 
- kubernetes wont allow you to modify the node name property of the pod after creation 
- Instead you can create a binding object and send post request to pod api. 
- if there is no scheduler, the pods will continue to be in a pending state. 

  
Manually schedule the pod on node01.
Delete and recreate the POD if necessary.
---
apiVersion: v1
kind: Pod
metadata:
  name: nginx
spec:
  containers:
  -  image: nginx
     name: nginx
  nodeName: node01 ------------------Adding this allows you to manually specify the pod to a node, however do need to delete the pod and create file again.
    
Now schedule the same pod on the controlplane node.
Delete and recreate the POD if necessary.
  
---
apiVersion: v1
kind: Pod
metadata:
  name: nginx
spec:
  containers:
  -  image: nginx
     name: nginx
  nodeName: controlplane
~                                                                                                                                                               
~               
     
Labels and Selectors:
     
- Are used to identify where objects belong to
- for each object attach labels as per your needs i.e app / function
- specify condition to filer out objects
- In pod definition file, you can specify under metadata section called labels and add as many labels as you like. 
- once pod is created you can select pod with specific labels using this command kubectl get pods --selector app=App1
- For example to create a replicaset consisting of 3 different pods you can labels the pod definition and use label the pod definition - 
  and group the pods by adding the spec.
- Need to be aware that there are 2 sections with labels
     - the top part where it specifies the label, is the label of the replicaset, the labels under the template section is the labels of the pods. 
apiVersion:
