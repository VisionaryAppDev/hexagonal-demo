# Why Hexagonal Architecture? 
- Care about domain
- Don't care about technology as it's adapter -> to achieve Decoupling 


# Package
- Application
- Domain
- Framework


# Framework (Adapter)
- input.rest
    - converter
    - deserializer
    - request (like a dto)
        - switch
            - CreateSwitch
        - router
            - AddRouter
            - CreateRouter
        - network
            - AddNetwork
    - RouterManagementAdapter (like a controller)
        + Inject RouterManagementUseCase
        - @GET retreiveRouter(..)
- output.mysql
    - data
    - mappers
    - repository

# Application (Ports)
The Application hexagon is where we abstractly deal with application-specific tasks. I mean abstract because we're not directly dealing with technology concerns yet. This hexagon expresses the software's user intent and features based on the Domain hexagon's business rules.

Ports: interface define to 
- ports
    - input
    If use cases are just interfaces describing what the software does, we still need to implement the use case interface. That's the role of the input port. By being a component that's directly attached to use cases, at the Application level, input ports allow us to implement software intent on domain terms.

        - NetworkManagementInputPort (class implement UseCase)
            + Inject RouterManagementOutputPort
            - createNetwork()
            - addNetworkToSwitch()
            - removeNetworkFromSwitch() 
    - output
    There are situations in which a use case needs to fetch data from external resources to achieve its goals. That's the role of output ports, which are represented as interfaces describing, in a technology-agnostic way, which kind of data a use case or input port would need to get from outside to perform its operations. I say agnostic because output ports don't care if the data comes from a particular relational database technology or a filesystem, for example. 

        - RouterManagementOutputPort (interface)
            - retrieveRouter()
            - removeRouter()
            - persistRouter()
- usecases
Use cases represent the system's behavior through application-specific operations, which exist within the software realm to support the domain's constraints. Use cases may interact directly with entities and other use cases, making them flexible components.

    - NetworkManagementUseCase (interface)
        - createNetwork()
        - addNetworkToSwitch()
        - removeNetworkFromSwitch()


# Domain
The least the dependency, the better. 

- entity
    - factory
        - RouterFactory
    - Equipment (abstract class)
    - Router (abstract class extend Equipment)
    - CoreRouter (extend Router)
        - validate spec using Specification
    - EdgeRouter (extend Router)
- exception
- service
    - NetworkService (class)
        - static filterAndRetrieveNetworks
        - static findNetwork
    - RouterService (class)
        - static filterAndRetrieveRouter
        - static findById
- specification
- vo (value object)
    - It contains only value and without identity and it's immutable.